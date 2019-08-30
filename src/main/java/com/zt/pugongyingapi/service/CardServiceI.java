package com.zt.pugongyingapi.service;

import com.zt.pugongyingapi.common.param.card.CardParam;
import com.zt.pugongyingapi.common.result.card.UserCardResult;
import com.zt.pugongyingapi.model.ZTCard;

public interface CardServiceI {

    UserCardResult bindCard(CardParam param,ZTCard card) throws Exception;

    ZTCard getCardInfo(CardParam param);
}
