package cn.ken.question.answering.system.model.log;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/30 <br/>
 * what: LogQaRecommend <br/>
 */
public class LogQaRequest extends LogQa{

    private List<Integer> responseTypeList;

    private String startTime;

    private String endTime;

    public LogQaRequest() {
    }

    public LogQaRequest(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public List<Integer> getResponseTypeList() {
        return responseTypeList;
    }

    public void setResponseTypeList(List<Integer> responseTypeList) {
        this.responseTypeList = responseTypeList;
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