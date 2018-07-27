package cn.ken.questionansweringsystem.model.knowledge;

/**
 * author: shangkun <br/>
 * date: 2018/7/25 <br/>
 * what: 答案 <br/>
 */
public class Answer {

    private String id;

    private String content;

    private Integer channelId;

    private String knowledgeId;

    public String getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(String knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }
}