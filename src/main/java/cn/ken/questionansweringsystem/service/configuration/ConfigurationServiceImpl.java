package cn.ken.questionansweringsystem.service.configuration;

import cn.ken.questionansweringsystem.mapper.configuration.ConfigurationMapper;
import cn.ken.questionansweringsystem.memorydb.ConfigurationDB;
import cn.ken.questionansweringsystem.model.configuration.Configuration;
import cn.ken.questionansweringsystem.utils.Base;
import cn.ken.questionansweringsystem.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author: shangkun <br/>
 * date: 2018/7/25 <br/>
 * what: 问答配置 <br/>
 */
@Service
public class ConfigurationServiceImpl extends Base implements ConfigurationService{
    @Autowired
    private ConfigurationMapper configurationMapper;

    @Override
    public String update(Configuration configuration) {
        String result = assemble(configuration);
        if(result!=null){
            return result;
        }
        configurationMapper.update(configuration);
        ConfigurationDB.configuration=configuration;
        return null;
    }

    @Override
    public Configuration get() throws Exception {
        return configurationMapper.get();
    }

    public String assemble(Configuration configuration){
        if(!StringUtils.lengthCheck(configuration.getWelcome(),0,500)){
            return "欢迎语不能为空或长度超过500";
        }
        if(configuration.getThresholdUpperLimit()==null ||
           configuration.getThresholdLowerLimit()==null ||
           configuration.getThresholdLowerLimit()<=0.00 ||
           configuration.getThresholdLowerLimit()>=1.00 ||
           configuration.getThresholdUpperLimit()>=1.00 ||
           configuration.getThresholdUpperLimit()<=0.00 ||
           configuration.getThresholdLowerLimit()>=
           configuration.getThresholdUpperLimit()){
            return "上下限阈值设置不正确";
        }
        if(configuration.getTimeout()==null){
            return "超时时间不能为空";
        }
        if(configuration.getRecommendQuestionNumber()==null){
            return "推荐问题个数不能为空";
        }
        if(configuration.getGreetingThresholdUpperLimit()==null ||
                configuration.getGreetingThresholdUpperLimit()<=0.00 ||
                configuration.getGreetingThresholdUpperLimit()>=1.00){
            return "寒暄上下限阈值设置不正确";
        }
        if(!StringUtils.lengthCheck(configuration.getUnknown(),0,500)){
            return "未知问题回复不能为空或长度超过500";
        }
        if(!StringUtils.lengthCheck(configuration.getHasAnswerAndRecommend(),0,500)){
            return "命中答案的提示语不能为空或长度超过500";
        }
        if(!StringUtils.lengthCheck(configuration.getHasAnswerAndRecommend(),0,500)){
            return "仅有推荐问的提示语不能为空或长度超过500";
        }
        if(configuration.getHotQuestionLimit()==null){
            return "热点问题个数不能为空";
        }
        return null;
    }
}
