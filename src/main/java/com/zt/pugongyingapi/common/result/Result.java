package com.zt.pugongyingapi.common.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result<T> {
    private int code;
    private String msg;
    private T data;
}
