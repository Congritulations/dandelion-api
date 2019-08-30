package com.zt.pugongyingapi.controller;

import com.aliyuncs.exceptions.ClientException;
import com.github.pagehelper.util.StringUtil;
import com.zt.pugongyingapi.common.param.user.LoginParam;
import com.zt.pugongyingapi.common.result.Result;
import com.zt.pugongyingapi.common.result.ResultEnum;
import com.zt.pugongyingapi.common.result.ResultUtil;
import com.zt.pugongyingapi.common.result.user.UserResult;
import com.zt.pugongyingapi.service.UserServiceI;
import com.zt.pugongyingapi.utils.Constant;
import com.zt.pugongyingapi.utils.DataUtil;
import com.zt.pugongyingapi.utils.RedisComponent;
import com.zt.pugongyingapi.utils.SmsSendUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("user")
public class LoginController {

    @Autowired
    private RedisComponent component;
    @Autowired
    private UserServiceI userServiceI;
    @Autowired
    private RedisComponent redisComponent;


    /**
     * 手机号登陆
     * @param param
     * @return
     */
    @PostMapping("login")
    public Result login(LoginParam param){
        try {
            if (StringUtil.isEmpty(param.getPhone()) || StringUtil.isEmpty(param.getCode()))
                return ResultUtil.fail(ResultEnum.FAIL_MISSING_PARAMETER);
            String flag = redisComponent.get(Constant.MSG_CODE_KEY + param.getPhone());
            if (StringUtil.isEmpty(flag))
                return ResultUtil.fail(ResultEnum.FAIL_MISSING_CODE);
            if (!param.getCode().equals(flag))
                return ResultUtil.fail(ResultEnum.ERROR_MSG_CODE);
            return userServiceI.userLogin(param);
        } catch (Exception e) {
            log.error("登陆发生错误:{}",e);
            return ResultUtil.error(ResultEnum.ERROR_SYSTEM);
        }
    }


    @GetMapping("noLogin")
    public Result<String> noLogin() {
        return ResultUtil.fail(ResultEnum.FAIL_NEED_LOGIN);
    }


    @PostMapping("loginOut")
    public Result loginOut(String phone){
        return null;
    }


    /**
     * 发送验证码
     * @param phone
     * @return
     */
    @GetMapping("sendMsgCode")
    public Result sendMsgCode(String phone){
        try {
            if (StringUtil.isEmpty(phone))
                return ResultUtil.fail(ResultEnum.FAIL_MISSING_PARAMETER);
            String regex = "1[0-9] {10}";
            if(phone.matches(regex))
                return ResultUtil.fail(ResultEnum.FAIL_PHONE_ERROR);
            //五分钟内，每一号码只能发送5条短信
            Integer times = redisComponent.getInteger(Constant.REDIS_COUNT_KEY+Constant.MSG_CODE_KEY + phone);
            times = times == null ? 0 : times;

            if(times < 5){//允许发送
                redisComponent.increase(Constant.REDIS_COUNT_KEY+Constant.MSG_CODE_KEY + phone,1);
                if(times == 0){
                    redisComponent.expire(Constant.REDIS_COUNT_KEY+Constant.MSG_CODE_KEY + phone,60*5);
                }
            }else{//不允许发送
                return ResultUtil.fail(ResultEnum.FAIL_SEND_CODE_PER_MINITE);
            }
            String code = DataUtil.randomCode();
            boolean result = SmsSendUtil.sendMessage(phone, code,null);
            if (result) {
                component.saveDataWithTime(Constant.MSG_CODE_KEY + phone, code, Constant.MSG_CODE_IN_REDIS_TIME);
                log.info("成功给手机：{}发送验证码：{}",phone,code);
                return ResultUtil.success(ResultEnum.SUCCESS_SEND_CODE);
            }else {
                return ResultUtil.fail(ResultEnum.FAIL_SEND_CODE);
            }
        } catch (ClientException e) {
            log.error("短信发送异常：{}",e);
            return ResultUtil.error(ResultEnum.ERROR_SEND_MSG);
        }
    }
}
