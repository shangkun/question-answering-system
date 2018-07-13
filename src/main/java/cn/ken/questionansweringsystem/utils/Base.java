package cn.ken.questionansweringsystem.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: 基础类 <br/>
 */
public class Base {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
}
