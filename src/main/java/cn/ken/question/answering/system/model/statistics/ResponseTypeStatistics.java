package cn.ken.question.answering.system.model.statistics;

/**
 * author: shangkun <br/>
 * date: 2018/7/30 <br/>
 * what: ResponseTypeStatistics <br/>
 */
public class ResponseTypeStatistics {

    private Integer hasAnswerAndRecommend;

    private Integer hasAnswer;

    private Integer hasRecommend;

    private Integer greeting;

    private Integer unknown;

    private Integer others;

    private String time;

    public ResponseTypeStatistics() {
    }

    public ResponseTypeStatistics(Integer hasAnswerAndRecommend, Integer hasAnswer, Integer hasRecommend, Integer greeting, Integer unknown, Integer others) {
        this.hasAnswerAndRecommend = hasAnswerAndRecommend;
        this.hasAnswer = hasAnswer;
        this.hasRecommend = hasRecommend;
        this.greeting = greeting;
        this.unknown = unknown;
        this.others = others;
    }

    public Integer getHasAnswerAndRecommend() {
        return hasAnswerAndRecommend;
    }

    public void setHasAnswerAndRecommend(Integer hasAnswerAndRecommend) {
        this.hasAnswerAndRecommend = hasAnswerAndRecommend;
    }

    public Integer getHasAnswer() {
        return hasAnswer;
    }

    public void setHasAnswer(Integer hasAnswer) {
        this.hasAnswer = hasAnswer;
    }

    public Integer getHasRecommend() {
        return hasRecommend;
    }

    public void setHasRecommend(Integer hasRecommend) {
        this.hasRecommend = hasRecommend;
    }

    public Integer getGreeting() {
        return greeting;
    }

    public void setGreeting(Integer greeting) {
        this.greeting = greeting;
    }

    public Integer getUnknown() {
        return unknown;
    }

    public void setUnknown(Integer unknown) {
        this.unknown = unknown;
    }

    public Integer getOthers() {
        return others;
    }

    public void setOthers(Integer others) {
        this.others = others;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
