package cn.ken.questionansweringsystem.model.knowledge;

import java.util.Date;

/**
 * author: shangkun <br/>
 * date: 2018/7/25 <br/>
 * what: 词库 <br/>
 */
public class Lexicon {

    private String id;

    private String topic;

    private String topicSet;

    private Integer type;

    private Date modifyTime;

    private String modifierId;

    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopicSet() {
        return topicSet;
    }

    public void setTopicSet(String topicSet) {
        this.topicSet = topicSet;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}