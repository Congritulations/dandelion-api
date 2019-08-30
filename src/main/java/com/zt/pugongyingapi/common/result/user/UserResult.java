package com.zt.pugongyingapi.common.result.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserResult {

    private String id;
    private String username;
    private String phone;
    private String picture;
    private String nickname;
    private String openId;
    private Integer state;
    private Date createTime;
    private String token;
    private String remark;
    private boolean hasCard = false;
}
