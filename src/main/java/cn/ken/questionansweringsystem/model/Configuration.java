package cn.ken.questionansweringsystem.model;

public class Configuration {
    private String welcome;

    private Double thresholdUpperLimit;

    private Double thresholdLowerLimit;

    private Integer timeout;

    private Integer recommendQuestionNumber;

    private Double greetingThresholdUpperLimit;

    private String unknown;

    private String hasAnswerAndRecommend;

    private String hasRecommend;

    private Integer hotQuestionLimit;

    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome == null ? null : welcome.trim();
    }

    public Double getThresholdUpperLimit() {
        return thresholdUpperLimit;
    }

    public void setThresholdUpperLimit(Double thresholdUpperLimit) {
        this.thresholdUpperLimit = thresholdUpperLimit;
    }

    public Double getThresholdLowerLimit() {
        return thresholdLowerLimit;
    }

    public void setThresholdLowerLimit(Double thresholdLowerLimit) {
        this.thresholdLowerLimit = thresholdLowerLimit;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getRecommendQuestionNumber() {
        return recommendQuestionNumber;
    }

    public void setRecommendQuestionNumber(Integer recommendQuestionNumber) {
        this.recommendQuestionNumber = recommendQuestionNumber;
    }

    public Double getGreetingThresholdUpperLimit() {
        return greetingThresholdUpperLimit;
    }

    public void setGreetingThresholdUpperLimit(Double greetingThresholdUpperLimit) {
        this.greetingThresholdUpperLimit = greetingThresholdUpperLimit;
    }

    public String getUnknown() {
        return unknown;
    }

    public void setUnknown(String unknown) {
        this.unknown = unknown == null ? null : unknown.trim();
    }

    public String getHasAnswerAndRecommend() {
        return hasAnswerAndRecommend;
    }

    public void setHasAnswerAndRecommend(String hasAnswerAndRecommend) {
        this.hasAnswerAndRecommend = hasAnswerAndRecommend == null ? null : hasAnswerAndRecommend.trim();
    }

    public String getHasRecommend() {
        return hasRecommend;
    }

    public void setHasRecommend(String hasRecommend) {
        this.hasRecommend = hasRecommend == null ? null : hasRecommend.trim();
    }

    public Integer getHotQuestionLimit() {
        return hotQuestionLimit;
    }

    public void setHotQuestionLimit(Integer hotQuestionLimit) {
        this.hotQuestionLimit = hotQuestionLimit;
    }
}