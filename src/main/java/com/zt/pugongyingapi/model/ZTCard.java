package com.zt.pugongyingapi.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ZTCard {
    private String id;
    private String cardNum;
    private String cardPass;
    private Integer cardType;
    private Integer cardState;
    private BigDecimal cardPrice;
    private String remark;
}
