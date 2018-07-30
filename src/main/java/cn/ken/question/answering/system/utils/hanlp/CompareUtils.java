package cn.ken.question.answering.system.utils.hanlp;

import cn.ken.question.answering.system.model.qa.Retrieval;

import java.util.Comparator;

/**
 * author: shangkun <br/>
 * date: 2018/7/16 <br/>
 * what: CompareUtils <br/>
 */
public class CompareUtils implements Comparator<Retrieval> {
    @Override
    public int compare(Retrieval o1, Retrieval o2) {
        return -((int)(o1.getSimilarity()*100)-(int)(o2.getSimilarity()*100));
    }
}
