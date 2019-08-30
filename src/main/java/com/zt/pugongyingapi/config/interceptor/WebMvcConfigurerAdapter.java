package com.zt.pugongyingapi.config.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfigurerAdapter implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/**")
			    .excludePathPatterns("/user/login")
			    .excludePathPatterns("/user/sendMsgCode")
			    .excludePathPatterns("/user/noLogin");
	}


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
				.allowedOrigins("*")
				.allowCredentials(true)
				.allowedMethods("GET","POST","PUT","DELETE")
				.maxAge(3600);
    }

    @Bean
	public LoginInterceptor loginInterceptor(){
		return new LoginInterceptor();
	}

}
