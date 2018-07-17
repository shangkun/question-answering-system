package cn.ken.questionansweringsystem.redis;

import cn.ken.questionansweringsystem.JUnit4BaseConfig;
import cn.ken.questionansweringsystem.utils.RedisUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * author: shangkun <br/>
 * date: 2018/7/17 <br/>
 * what: custom <br/>
 */
public class TestRedis extends JUnit4BaseConfig {

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void test(){
        redisUtils.set("foo", "bar");
        System.out.println(redisUtils.get("foo"));
        redisUtils.delete("bar");
    }
}
