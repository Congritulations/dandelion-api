package com.zt.pugongyingapi.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisComponent {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    /**
     * 如果time大于0，那么就设置有失效时间的key-value
     */
    public void saveDataWithTime(String key,String value,long time){
        redisTemplate.opsForValue().set(key, value);
        if (time > 0) redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    public String get(String  key){
        try {
            Object value = redisTemplate.opsForValue().get(key);
            if(value == null){
                return null;
            }else{
                return value.toString();
            }
        } catch (Exception e) {
            log.error("ERROR-redis:{}",e);
            e.printStackTrace();
            return null;
        }
    }

    /*
     * 根据key精确查询存储的数字类型的值
     */
    public long getNum(String key){
        try {
            Object count = redisTemplate.opsForValue().get(key);

            if(count == null){
                return 0;
            }

            return Integer.valueOf(count.toString());
        } catch (Exception e) {
            log.error("redis错误:{}",e);
            return 0;
        }
    }

    public Integer getInteger(String key){
        Object value = redisTemplate.opsForValue().get(key);
        return value == null ? null : Integer.valueOf(value.toString());
    }

    /**
     * 获取所有的key
     */
    public Set<String> getAllKey(){
        Set<String> keys = redisTemplate.keys("*");
        return keys;
    }

    /**
     * 判断是否重复提交
     * @param key
     * @return
     */
    public boolean resubmit(String key) {
        //自增1
        redisTemplate.opsForValue().increment(key, 1);
        //设置超时时间
        expire(key,3);
        //获取此key对应的值
        Integer value = getInteger(key);
        if(value == 1){
            return false;
        }
        return true;
    }

    public void expire(String key,long time){
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 将key对应的值增加i
     */
    public void increase(String key, int i) {
        redisTemplate.opsForValue().increment(key, i);
    }

    /**
     * 设置key的存在时间为i秒
     */
    public void expire(String key, int i) {
        redisTemplate.expire(key, i, TimeUnit.SECONDS);
    }

    public boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }
}
