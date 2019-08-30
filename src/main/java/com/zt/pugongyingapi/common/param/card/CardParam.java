package com.zt.pugongyingapi.common.param.card;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class CardParam {
    private String id;
    private String cardId;
    private String cardNum;
    private String cardPass;
    private String userId;
    private Date createTime;
    private Date endTime;
}
