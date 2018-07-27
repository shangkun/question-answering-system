package cn.ken.questionansweringsystem.common;

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
    businessWord(1,"业务词");

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
