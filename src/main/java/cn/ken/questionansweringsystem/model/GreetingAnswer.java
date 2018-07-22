package cn.ken.questionansweringsystem.model;

public class GreetingAnswer {
    private String id;

    private String answer;

    private Integer channelId;

    private String greetingId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getGreetingId() {
        return greetingId;
    }

    public void setGreetingId(String greetingId) {
        this.greetingId = greetingId == null ? null : greetingId.trim();
    }
}