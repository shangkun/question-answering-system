package cn.ken.question.answering.system.mapper.configuration;

import cn.ken.question.answering.system.model.configuration.Configuration;

public interface ConfigurationMapper{
    /**
     * 修改配置
     * @param configuration
     * @return
     */
    public int update(Configuration configuration);

    public Configuration get();
}