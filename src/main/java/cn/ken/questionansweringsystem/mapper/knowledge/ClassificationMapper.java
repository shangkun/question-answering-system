package cn.ken.questionansweringsystem.mapper.knowledge;

import cn.ken.questionansweringsystem.mapper.BaseMapper;
import cn.ken.questionansweringsystem.model.knowledge.Classification;
import cn.ken.questionansweringsystem.model.knowledge.ClassificationRequest;

import java.util.List;

public interface ClassificationMapper extends BaseMapper<Classification,String> {

    List<Classification> getByAttribute(ClassificationRequest request);

    int countByAttribute(ClassificationRequest request);

    int countByName(String name,String pId);

    int countByIdAndName(String id, String name,String pId);

    int deleteByIdList(List<String> list);

    List<String> getByPId(String pId);
}