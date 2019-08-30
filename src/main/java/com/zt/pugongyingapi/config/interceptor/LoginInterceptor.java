package com.zt.pugongyingapi.config.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zt.pugongyingapi.utils.Constant;
import com.zt.pugongyingapi.utils.RedisComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisComponent redisComponent;


    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        String token = req.getHeader("token");
		if(token == null){//header中没有token说明没登录
			req.getRequestDispatcher("/user/noLogin").forward(req, res);
			return false;
		}else{//有token，那么验证token有效性
			String userInfoJsonStr = redisComponent.get(token);
			if(userInfoJsonStr == null){//token无效
                req.getRequestDispatcher("/user/noLogin").forward(req, res);
				return false;
			}else{//token有效
				req.setAttribute("userInfoJsonStr", userInfoJsonStr);
                redisComponent.saveDataWithTime(token, userInfoJsonStr, Constant.USER_INFO_IN_REDIS_TIME);

				JSONObject fromObject = JSONObject.parseObject(userInfoJsonStr);
				String userId = fromObject.getString("id");

				log.info("userId{} {},路径{},get：{}",userId,req.getServletPath(),req.getQueryString());
			}
		}
        return true;
    }

}
