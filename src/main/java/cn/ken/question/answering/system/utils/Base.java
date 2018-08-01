package cn.ken.question.answering.system.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: 基础类 <br/>
 */
public class Base {
    public Logger logger = LogManager.getLogger(this.getClass());
    public Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
}
