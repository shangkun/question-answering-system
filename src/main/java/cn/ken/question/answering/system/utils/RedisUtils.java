package cn.ken.question.answering.system.utils;

import cn.ken.question.answering.system.model.qa.QuestionAnswerHistory;
import com.google.gson.JsonSyntaxException;
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
public class RedisUtils extends Base{

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

    public QuestionAnswerHistory getHistory(String key){
        String historyJson = get(key);
        if(StringUtils.isEmpty(historyJson)){
            return null;
        }
        try {
            QuestionAnswerHistory history = gson.fromJson(historyJson,QuestionAnswerHistory.class);
            return history;
        } catch (JsonSyntaxException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }
}
