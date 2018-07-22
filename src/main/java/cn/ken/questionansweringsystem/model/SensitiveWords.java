package cn.ken.questionansweringsystem.model;

public class SensitiveWords {
    private String id;

    private String topic;

    private String topicSet;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic == null ? null : topic.trim();
    }

    public String getTopicSet() {
        return topicSet;
    }

    public void setTopicSet(String topicSet) {
        this.topicSet = topicSet == null ? null : topicSet.trim();
    }
}