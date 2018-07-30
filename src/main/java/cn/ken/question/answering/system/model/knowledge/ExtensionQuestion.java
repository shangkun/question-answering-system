package cn.ken.question.answering.system.model.knowledge;

import com.hankcs.hanlp.seg.common.Term;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/25 <br/>
 * what: 扩展问 <br/>
 */
public class ExtensionQuestion {

    private String id;

    private String title;

    private String knowledgeId;

    private List<Term> termList;

    public List<Term> getTermList() {
        return termList;
    }

    public void setTermList(List<Term> termList) {
        this.termList = termList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(String knowledgeId) {
        this.knowledgeId = knowledgeId;
    }
}