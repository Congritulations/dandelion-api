package com.zt.pugongyingapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Log {

    private String id;
    private String userId;
    private Integer type;
    private String remoteIp;
    private String content;
    private Date createTime;
    private String remark;

}
