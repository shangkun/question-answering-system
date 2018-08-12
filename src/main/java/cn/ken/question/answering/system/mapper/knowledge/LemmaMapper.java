package cn.ken.question.answering.system.mapper.knowledge;

import cn.ken.question.answering.system.mapper.BaseMapper;
import cn.ken.question.answering.system.model.knowledge.Lemma;

import java.util.List;

public interface LemmaMapper extends BaseMapper<Lemma,String> {

    int batchInsert(List<Lemma> list);

    int count();

    List<Lemma> selectByPage(int index,int pageSize);
}