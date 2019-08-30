package com.zt.pugongyingapi.mapper;

import com.zt.pugongyingapi.common.param.user.LoginParam;
import com.zt.pugongyingapi.common.result.user.UserResult;

public interface UserMapper {

    UserResult getUserInfo(LoginParam param);

    void save(UserResult param);

    Integer hasCrad(LoginParam param);
}
