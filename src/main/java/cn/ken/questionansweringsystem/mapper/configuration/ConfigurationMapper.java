package cn.ken.questionansweringsystem.mapper.configuration;

import cn.ken.questionansweringsystem.model.configuration.Configuration;

public interface ConfigurationMapper{
    /**
     * 修改配置
     * @param configuration
     * @return
     */
    public int update(Configuration configuration);

    public Configuration get();
}