package cn.ken.questionansweringsystem.common;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: 枚举 <br/>
 */
public enum Enum {

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
