package com.zt.pugongyingapi.mapper;

import com.zt.pugongyingapi.common.param.card.CardParam;
import com.zt.pugongyingapi.model.ZTCard;

public interface CardMapper {

    void bindCard(CardParam param);

    ZTCard getCardInfo(CardParam param);
}
