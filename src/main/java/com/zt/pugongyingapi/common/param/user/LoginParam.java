package com.zt.pugongyingapi.common.param.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LoginParam {

    private String id;
    private String username;
    private String code;
    private String phone;
    private String picture;
    private String nickname;
    private String openId;
    private Integer state;
    private Date createTime;
    private String remark;
}
