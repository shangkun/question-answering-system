package cn.ken.questionansweringsystem.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * author: shangkun <br/>
 * date: 2018/7/17 <br/>
 * what: RedisUtils <br/>
 */
@Repository
public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    public void set(String key,String value){
        redisTemplate.opsForValue().set(key,value);
    }

    public void set(String key,String value,int second){
        redisTemplate.opsForValue().set(key,value,second, TimeUnit.SECONDS);
    }

    public void delete(String key){
        redisTemplate.delete(key);
    }

    public String get(String key){
        Object o = redisTemplate.opsForValue().get(key);
        return o!=null?o.toString():"";
    }
}