package cn.ken.questionansweringsystem.model.knowledge;

/**
 * author: shangkun <br/>
 * date: 2018/7/25 <br/>
 * what: 寒暄回答 <br/>
 */
public class GreetingAnswer {

    private String id;

    private String answer;

    private Integer channelId;

    private String greetingId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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
        this.greetingId = greetingId;
    }
}