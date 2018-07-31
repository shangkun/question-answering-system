package cn.ken.question.answering.system.model.statistics;

/**
 * author: shangkun <br/>
 * date: 2018/7/30 <br/>
 * what: KnowledgeRanking <br/>
 */
public class KnowledgeRanking {

    private String knowledgeId;

    private String knowledgeTitle;

    private String time;

    private Integer accessNumber;

    public KnowledgeRanking() {
    }

    public KnowledgeRanking(String knowledgeId, String time, Integer accessNumber) {
        this.knowledgeId = knowledgeId;
        this.time = time;
        this.accessNumber = accessNumber;
    }

    public String getKnowledgeTitle() {
        return knowledgeTitle;
    }

    public void setKnowledgeTitle(String knowledgeTitle) {
        this.knowledgeTitle = knowledgeTitle;
    }

    public String getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(String knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getAccessNumber() {
        return accessNumber;
    }

    public void setAccessNumber(Integer accessNumber) {
        this.accessNumber = accessNumber;
    }
}
