package com.zt.pugongyingapi.service.impl;

import com.zt.pugongyingapi.mapper.LogMapper;
import com.zt.pugongyingapi.model.Log;
import com.zt.pugongyingapi.service.LogServiceI;
import com.zt.pugongyingapi.utils.DataUtil;
import com.zt.pugongyingapi.utils.IPUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class LogServiceImpl implements LogServiceI {

    @Autowired
    private LogMapper logMapper;
    @Autowired
    private HttpServletRequest request;

    @Override
    public void saveLog(Log log) {
        log.setId(DataUtil.UUID());
        log.setCreateTime(new Date());
        log.setRemoteIp(IPUtils.getIpAddr(request));
        logMapper.saveLog(log);
    }
}
