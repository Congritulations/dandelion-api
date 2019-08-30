package com.zt.pugongyingapi.service.impl;

import com.alibaba.fastjson.JSON;
import com.zt.pugongyingapi.common.param.user.LoginParam;
import com.zt.pugongyingapi.common.result.Result;
import com.zt.pugongyingapi.common.result.ResultUtil;
import com.zt.pugongyingapi.common.result.user.UserResult;
import com.zt.pugongyingapi.mapper.UserMapper;
import com.zt.pugongyingapi.model.Log;
import com.zt.pugongyingapi.service.LogServiceI;
import com.zt.pugongyingapi.service.UserServiceI;
import com.zt.pugongyingapi.utils.Constant;
import com.zt.pugongyingapi.utils.DataUtil;
import com.zt.pugongyingapi.utils.RedisComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class UserServiceImpl implements UserServiceI {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisComponent redisComponent;
    @Autowired
    private LogServiceI logServiceI;


    @Override
    public UserResult getUserInfo(LoginParam param) {
        return userMapper.getUserInfo(param);
    }


    @Override
    public void save(UserResult param) {
        userMapper.save(param);
    }

    @Override
    public Result userLogin(LoginParam param) {
        UserResult userInfo = getUserInfo(param);
        if (null != userInfo){
            boolean has = redisComponent.hasKey(Constant.USER_PHONE_KEY+param.getPhone());
            if (has) {
                redisComponent.expire(Constant.USER_PHONE_KEY + param.getPhone(), Constant.USER_INFO_IN_REDIS_TIME);
            }else {
                redisComponent.saveDataWithTime(Constant.USER_PHONE_KEY + param.getPhone(), JSON.toJSONString(userInfo),Constant.USER_INFO_IN_REDIS_TIME);
            }
            log.info("用户:{}登陆成功",JSON.toJSONString(userInfo));
            userInfo.setToken(Constant.USER_PHONE_KEY + param.getPhone());

            //写日志
            Log log = new Log();
            log.setContent("用户："+userInfo.getPhone()+"登陆");
            log.setType(3);
            log.setUserId(userInfo.getId());

            logServiceI.saveLog(log);
        }else {
            userInfo.setId(DataUtil.UUID());
            userInfo.setPhone(param.getPhone());
            userInfo.setCreateTime(new Date());
            userInfo.setToken(Constant.USER_PHONE_KEY + param.getPhone());
            save(userInfo);

            UserResult ztUser =getUserInfo(param);

            redisComponent.saveDataWithTime(Constant.USER_PHONE_KEY + ztUser.getPhone(), JSON.toJSONString(ztUser),Constant.USER_INFO_IN_REDIS_TIME);
            log.info("用户:{}登陆成功",JSON.toJSONString(ztUser));


            //写日志
            Log log = new Log();
            log.setContent("用户："+ztUser.getPhone()+"登陆");
            log.setType(3);
            log.setUserId(ztUser.getId());

            logServiceI.saveLog(log);

        }
        Integer count = userMapper.hasCrad(param);
        if (count > 0)
            userInfo.setHasCard(true);
        return ResultUtil.success(userInfo);
    }
}
