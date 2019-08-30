package com.zt.pugongyingapi.common.result.card;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zt.pugongyingapi.config.JsonDateSerializer;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserCardResult {

    private String cardNum;
    private String cardId;
    private Integer cardType;
    private Integer cardState;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date createTime;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date endTime;
    private String remark;
}
