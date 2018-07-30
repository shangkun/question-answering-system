package cn.ken.question.answering.system.model.knowledge;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/27 <br/>
 * what: 知识 <br/>
 */
public class KnowledgeRequest extends Knowledge{

    private int index;

    private int pageSize;

    private String startTime;

    private String endTime;

    private List<String> classificationIdList;

    private List<String> idList;

    private List<String> extensionQuestionTitleList;

    public KnowledgeRequest() {
    }

    public KnowledgeRequest(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public List<String> getExtensionQuestionTitleList() {
        return extensionQuestionTitleList;
    }

    public void setExtensionQuestionTitleList(List<String> extensionQuestionTitleList) {
        this.extensionQuestionTitleList = extensionQuestionTitleList;
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

    public List<String> getClassificationIdList() {
        return classificationIdList;
    }

    public void setClassificationIdList(List<String> classificationIdList) {
        this.classificationIdList = classificationIdList;
    }
}
