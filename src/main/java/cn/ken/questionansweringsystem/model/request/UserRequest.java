package cn.ken.questionansweringsystem.model.request;

import cn.ken.questionansweringsystem.model.User;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: 用户请求类 <br/>
 */
public class UserRequest extends User{

    private int index;

    private int pageSize;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
