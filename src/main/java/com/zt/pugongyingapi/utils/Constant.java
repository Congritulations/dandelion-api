package com.zt.pugongyingapi.utils;

public class Constant {
    //用户信息在redis中的保存时间（秒）
    public static final long USER_INFO_IN_REDIS_TIME = 60*60*24*7;
    //密码盐
    public static final String PASS_WORD_SALT = "!@o.oump#$ump%ump@pump***p^_^&*(a.a)";

    //短信验证码在redis中存储的时间（秒）
    public static final long MSG_CODE_IN_REDIS_TIME = 60*10;



    //短信
    public static final String SMS_SEND_URL = "dysmsapi.aliyuncs.com";

    public static final String PRODUCT_NAME = "Dysmsapi";

    public static final String APPKEY = "LTAI7NAYJ7LIhGMU";

    public static final String APPSECRET = "I2K97LN6FvPkkWV4Lfkfx6eqkdRbWd";

    public static final String SIGN_NAME = "蒲公英医学";

    public static final String VALITE_CODE_TEMPLATE_CODE = "SMS_172886259";

    //key
    public static final String MSG_CODE_KEY = "msg.code.";

    public static final String REDIS_COUNT_KEY = "count.";

    public static final String USER_PHONE_KEY = "user.phone.";


}
