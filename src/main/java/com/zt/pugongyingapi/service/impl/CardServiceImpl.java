package com.zt.pugongyingapi.service.impl;

import com.zt.pugongyingapi.common.param.card.CardParam;
import com.zt.pugongyingapi.common.result.card.UserCardResult;
import com.zt.pugongyingapi.mapper.CardMapper;
import com.zt.pugongyingapi.model.Log;
import com.zt.pugongyingapi.model.ZTCard;
import com.zt.pugongyingapi.service.CardServiceI;
import com.zt.pugongyingapi.service.LogServiceI;
import com.zt.pugongyingapi.utils.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class CardServiceImpl implements CardServiceI {


    @Autowired
    private CardMapper cardMapper;
    @Autowired
    private LogServiceI logServiceI;

    @Override
    public UserCardResult bindCard(CardParam param,ZTCard card)  throws Exception{
        UserCardResult result = new UserCardResult();
        param.setId(DataUtil.UUID());
        param.setCreateTime(new Date());
        param.setCardId(card.getId());
        Integer type = card.getCardType();
        switch(type){
            case 0:
                param.setEndTime(DataUtil.yearsLater(1));
                break;
            case 1:
                param.setEndTime(DataUtil.yearsLater(2));
                break;
            case 2:
                param.setEndTime(DataUtil.yearsLater(3));
                break;
            default:
                param.setEndTime(DataUtil.yearsLater(1));
                break;
        }
        cardMapper.bindCard(param);
        log.info("用户：{}绑定卡:{}成功",param.getUserId(),card.getCardNum());

        result.setCardId(card.getId());
        result.setCardNum(card.getCardNum());
        result.setCardState(1);
        result.setCardType(card.getCardType());
        result.setCreateTime(param.getCreateTime());
        result.setEndTime(param.getEndTime());


        Log logs = new Log();
        logs.setContent("用户："+param.getUserId()+"绑定卡:"+card.getCardNum()+"成功!");
        logs.setUserId(param.getUserId());
        logs.setType(0);
        logServiceI.saveLog(logs);

        return result;
    }

    @Override
    public ZTCard getCardInfo(CardParam param) {
        return cardMapper.getCardInfo(param);
    }
}
