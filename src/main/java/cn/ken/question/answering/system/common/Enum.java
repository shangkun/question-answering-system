package cn.ken.question.answering.system.common;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: 枚举 <br/>
 */
public enum Enum {

    allChannel(100,"所有渠道"),
    webChannel(101,"网页端"),
    appChannel(102,"手机端"),
    wechatChannel(103,"微信端"),

    hasAnswerAndRecommendResponse(104,"即有答案又有推荐问的回复"),
    hasAnswerResponse(105,"只有答案的回复"),
    hasRecommendResponse(106,"只有推荐问的回复"),
    greetingResponse(107,"寒暄回复"),
    unknowResponse(108,"未知回复"),
    othersResponse(109,"其他类型回复"),

    businessWord(1,"业务词"),
    businessNature(1,"business");

    private int value;
    private String info;

    public int getValue() {
        return value;
    }

    public String getInfo() {
        return info;
    }

    Enum(int value, String info) {
        this.value = value;
        this.info = info;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
