package cn.ken.question.answering.system.mapper.configuration;

import cn.ken.question.answering.system.model.configuration.SensitiveWordRequest;
import cn.ken.question.answering.system.mapper.BaseMapper;
import cn.ken.question.answering.system.model.configuration.SensitiveWord;

import java.util.List;

public interface SensitiveWordMapper extends BaseMapper<SensitiveWord,String> {

    int batchInert(List<SensitiveWord> list);

    int batchDelete(List<String> list);

    List<SensitiveWord> getByAttribute(SensitiveWordRequest request);

    int countByAttribute(SensitiveWordRequest request);

    int countByTopic(String topic);

    int countByIdAndTopic(String id,String topic);
}