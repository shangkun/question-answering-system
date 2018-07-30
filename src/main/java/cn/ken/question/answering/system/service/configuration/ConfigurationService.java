package cn.ken.question.answering.system.service.configuration;

import cn.ken.question.answering.system.model.configuration.Configuration;

/**
 * author: shangkun <br/>
 * date: 2018/7/25 <br/>
 * what: 问答配置 <br/>
 */
public interface ConfigurationService {

    public String update(Configuration configuration) throws Exception;

    public Configuration get() throws Exception;
}
