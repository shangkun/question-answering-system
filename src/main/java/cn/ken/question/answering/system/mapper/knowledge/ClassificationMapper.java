package cn.ken.question.answering.system.mapper.knowledge;

import cn.ken.question.answering.system.model.knowledge.ClassificationRequest;
import cn.ken.question.answering.system.mapper.BaseMapper;
import cn.ken.question.answering.system.model.knowledge.Classification;

import java.util.List;

public interface ClassificationMapper extends BaseMapper<Classification,String> {

    List<Classification> getByAttribute(ClassificationRequest request);

    int countByAttribute(ClassificationRequest request);

    int countByName(String name,String pId);

    int countByIdAndName(String id, String name,String pId);

    int deleteByIdList(List<String> list);

    List<String> getByPId(String pId);
}