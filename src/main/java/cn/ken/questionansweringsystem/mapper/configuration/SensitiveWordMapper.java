package cn.ken.questionansweringsystem.mapper.configuration;

import cn.ken.questionansweringsystem.mapper.BaseMapper;
import cn.ken.questionansweringsystem.model.configuration.SensitiveWord;
import cn.ken.questionansweringsystem.model.configuration.SensitiveWordRequest;

import java.util.List;

public interface SensitiveWordMapper extends BaseMapper<SensitiveWord,String> {

    int batchInert(List<SensitiveWord> list);

    int batchDelete(List<String> list);

    List<SensitiveWord> getByAttribute(SensitiveWordRequest request);

    int countByAttribute(SensitiveWordRequest request);

    int countByTopic(String topic);

    int countByIdAndTopic(String id,String topic);
}