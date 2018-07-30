package cn.ken.question.answering.system.model.knowledge;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/26 <br/>
 * what: 分类 <br/>
 */
public class ClassificationRequest extends Classification{

    private int index;

    private int pageSize;

    private String startTime;

    private String endTime;

    private List<String> modifierList;

    public ClassificationRequest() {
    }

    public ClassificationRequest(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<String> getModifierList() {
        return modifierList;
    }

    public void setModifierList(List<String> modifierList) {
        this.modifierList = modifierList;
    }
}
