package com.zt.pugongyingapi.service;

import com.zt.pugongyingapi.common.param.user.LoginParam;
import com.zt.pugongyingapi.common.result.Result;
import com.zt.pugongyingapi.common.result.user.UserResult;

public interface UserServiceI {

    UserResult getUserInfo(LoginParam param)throws Exception;

    void save(UserResult param)throws Exception;

    Result userLogin(LoginParam param)throws Exception;
}
