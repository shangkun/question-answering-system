package cn.ken.questionansweringsystem.mapper.knowledge;

import cn.ken.questionansweringsystem.mapper.BaseMapper;
import cn.ken.questionansweringsystem.model.knowledge.Greeting;
import cn.ken.questionansweringsystem.model.knowledge.GreetingRequest;

import java.util.List;

public interface GreetingMapper extends BaseMapper<Greeting,String>{

    List<Greeting> getByAttribute(GreetingRequest request);

    int countByAttribute(GreetingRequest request);

    int countByTitle(String title);

    int countByIdAndTitle(String id,String title);

    int deleteByIdList(List<String> list);

    int batchInsert(List<Greeting> list);
}