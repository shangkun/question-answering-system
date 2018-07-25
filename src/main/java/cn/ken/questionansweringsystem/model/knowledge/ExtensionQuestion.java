package cn.ken.questionansweringsystem.model.knowledge;

/**
 * author: shangkun <br/>
 * date: 2018/7/25 <br/>
 * what: 扩展问 <br/>
 */
public class ExtensionQuestion {

    private String id;

    private String title;

    private String knowledgeId;

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