package com.zt.pugongyingapi.common.result;

public enum  ResultEnum {

    //成功
    SUCCESS(200, "成功"),
    SUCCESS_EMPTY_DATA(201, "赞无数据"),
    SUCCESS_SEND_CODE(202, "验证码发送成功"),
    //失败
    FAIL(400, "失败"),
    FAIL_NEED_LOGIN(401, "未登录"),
    FAIL_MISSING_PARAMETER(402, "缺少关键参数"),
    FAIL_PHONE_ERROR(403, "手机号码错误"),
    FAIL_SEND_CODE(404, "验证码发送失败，请稍后重试"),
    FAIL_MISSING_CODE(405, "验证码错误"),
    FAIL_SEND_CODE_PER_MINITE(406, "五分钟内最多只能发送5次验证码"),
    FAIL_CARD_OR_PASS(407, "卡号或密码错误"),
    FAIL_MISSING_PERSON(408, "此用户不存在"),


    //错误
    //异常
    ERROR_SYSTEM(500,"系统错误"),
    ERROR_UNKNOWN(501, "未知错误"),
    ERROR_SEND_MSG(502, "发送短信异常"),
    ERROR_REQUEST_TYPE(503,"请求方式错误"),
    ERROR_MSG_CODE(505,"验证码错误");







    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
