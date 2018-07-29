package cn.ken.questionansweringsystem.model.qa;

/**
 * author: shangkun <br/>
 * date: 2018/7/16 <br/>
 * what: Retrieval <br/>
 */
public class Retrieval {
    private String knowledge;
    private String knowledgeTitle;
    private double similarity;

    public Retrieval(String knowledge, double similarity) {
        this.knowledge = knowledge;
        this.similarity = similarity;
    }

    public Retrieval(String knowledge, String knowledgeTitle, double similarity) {
        this.knowledge = knowledge;
        this.knowledgeTitle = knowledgeTitle;
        this.similarity = similarity;
    }

    public String getKnowledgeTitle() {
        return knowledgeTitle;
    }

    public void setKnowledgeTitle(String knowledgeTitle) {
        this.knowledgeTitle = knowledgeTitle;
    }

    public String getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(String knowledge) {
        this.knowledge = knowledge;
    }

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }
}
