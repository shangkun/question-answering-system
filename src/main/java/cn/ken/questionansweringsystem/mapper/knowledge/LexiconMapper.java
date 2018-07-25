package cn.ken.questionansweringsystem.mapper.knowledge;

import cn.ken.questionansweringsystem.mapper.BaseMapper;
import cn.ken.questionansweringsystem.model.knowledge.Lexicon;
import cn.ken.questionansweringsystem.model.knowledge.LexiconRequest;

import java.util.List;

public interface LexiconMapper extends BaseMapper<Lexicon,String>{

    int batchInert(List<Lexicon> list);

    int batchDelete(List<String> list);

    List<Lexicon> getByAttribute(LexiconRequest request);

    int countByAttribute(LexiconRequest request);

    int countByTopic(String topic);

    int countByIdAndTopic(String id,String topic);

}