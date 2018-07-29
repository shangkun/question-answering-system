package cn.ken.questionansweringsystem.model.knowledge;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/27 <br/>
 * what: 知识 <br/>
 */
public class GreetingRequest extends Greeting{

    private int index;

    private int pageSize;

    private String startTime;

    private String endTime;

    private List<String> idList;

    private List<String> greetingExtensionQuestionTitleList;

    public List<String> getGreetingExtensionQuestionTitleList() {
        return greetingExtensionQuestionTitleList;
    }

    public void setGreetingExtensionQuestionTitleList(List<String> greetingExtensionQuestionTitleList) {
        this.greetingExtensionQuestionTitleList = greetingExtensionQuestionTitleList;
    }

    public List<String> getIdList() {
        return idList;
    }

    public void setIdList(List<String> idList) {
        this.idList = idList;
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
}
