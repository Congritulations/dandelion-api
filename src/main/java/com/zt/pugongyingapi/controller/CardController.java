package com.zt.pugongyingapi.controller;

import com.github.pagehelper.util.StringUtil;
import com.zt.pugongyingapi.common.param.card.CardParam;
import com.zt.pugongyingapi.common.result.Result;
import com.zt.pugongyingapi.common.result.ResultEnum;
import com.zt.pugongyingapi.common.result.ResultUtil;
import com.zt.pugongyingapi.common.result.card.UserCardResult;
import com.zt.pugongyingapi.model.ZTCard;
import com.zt.pugongyingapi.service.CardServiceI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("card")
public class CardController {

    @Autowired
    private CardServiceI cardServiceI;


    /**
     * 用户绑卡
     * @param param
     * @return
     */
    @PostMapping("bind")
    public Result bind(CardParam param){
        try {
            if (StringUtil.isEmpty(param.getCardNum()) || StringUtil.isEmpty(param.getCardPass()))
                return ResultUtil.fail(ResultEnum.FAIL_MISSING_PARAMETER);
            ZTCard card = cardServiceI.getCardInfo(param);
            if (null == card)
                return ResultUtil.fail(ResultEnum.FAIL_CARD_OR_PASS);
            UserCardResult result = cardServiceI.bindCard(param,card);
            log.info("用户：{}绑定卡：{}成功",param.getUserId(),card.getCardNum());
            return ResultUtil.success(result);
        } catch (Exception e) {
            log.error("绑卡发生错误:{}",e);
            return ResultUtil.error(ResultEnum.ERROR_SYSTEM);
        }
    }
}
