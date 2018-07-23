package cn.ken.questionansweringsystem.model;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: 分页数据类 <br/>
 */
public class PageData<T> {

    private T data;

    private int total;

    public PageData(T data, int total) {
        this.data = data;
        this.total = total;
    }

    public PageData(int total) {
        this.total = total;
    }

    public PageData() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
