package com.zt.pugongyingapi.common.param.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserParam {

    private Integer id;
    private String username;
    private String password;
    private String phone;
    private String picture;
    private String nickname;
    private String openId;
    private Integer state;
    private Date create_time;
    private String remark;
}
