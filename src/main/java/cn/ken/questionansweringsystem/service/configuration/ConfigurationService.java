package cn.ken.questionansweringsystem.service.configuration;

import cn.ken.questionansweringsystem.model.configuration.Configuration;

/**
 * author: shangkun <br/>
 * date: 2018/7/25 <br/>
 * what: 问答配置 <br/>
 */
public interface ConfigurationService {

    public String update(Configuration configuration) throws Exception;

    public Configuration get() throws Exception;
}
