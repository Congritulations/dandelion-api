package com.zt.pugongyingapi.controller;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

public class BasicController {

    public static long initPageNo = 1;
    public static long initPageSize = 10;

    public String getUserName(HttpServletRequest req){
        JSONObject userInfoJsonObject = getUserInfoJsonObject(req);
        String userName = userInfoJsonObject.getString("userName");
        return userName;
    }

    public int getUserId(HttpServletRequest req){
        JSONObject userInfoJsonObject = getUserInfoJsonObject(req);
        Integer userId = userInfoJsonObject.getInteger("id");
        return userId;
    }

    public JSONObject getUserInfoJsonObject(HttpServletRequest req){
        String userInfoStr = (String) req.getAttribute("userInfoJsonStr");
        JSONObject userInfoJsonObject = JSONObject.parseObject(userInfoStr);
        return userInfoJsonObject;
    }

    public com.alibaba.fastjson.JSONObject initPageInfo(com.alibaba.fastjson.JSONObject param){
        param.put("starttime", param.get("starttime")==null?null:param.getString("starttime")+" 00:00:00");
        param.put("endtime", param.get("endtime")==null?null:param.getString("endtime")+" 23:59:59");

        param.put("pageNo", param.get("pageNo")==null?initPageNo:param.getLong("pageNo"));
        param.put("pageSize", param.get("pageSize")==null?initPageSize:param.getLong("pageSize"));
        return param;
    }

}

