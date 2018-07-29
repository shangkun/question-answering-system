package cn.ken.questionansweringsystem.model.qa;

import cn.ken.questionansweringsystem.model.LogQa;
import cn.ken.questionansweringsystem.model.LogQaRecommend;
import com.hankcs.hanlp.seg.common.Term;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/25 <br/>
 * what: 问答 <br/>
 */
public class QuestionAnswerHistory extends LogQa{

    private String segmentResult;

    private String deleteStopWordsResult;

    private String message;

    private List<Retrieval> retrievalList;

    private List<LogQaRecommend> recommendList;

    private Integer sequence;
    //问答主题
    private String topic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public List<LogQaRecommend> getRecommendList() {
        return recommendList;
    }

    public void setRecommendList(List<LogQaRecommend> recommendList) {
        this.recommendList = recommendList;
    }

    public List<Retrieval> getRetrievalList() {
        return retrievalList;
    }

    public void setRetrievalList(List<Retrieval> retrievalList) {
        this.retrievalList = retrievalList;
    }

    public String getSegmentResult() {
        return segmentResult;
    }

    public void setSegmentResult(String segmentResult) {
        this.segmentResult = segmentResult;
    }

    public String getDeleteStopWordsResult() {
        return deleteStopWordsResult;
    }

    public void setDeleteStopWordsResult(String deleteStopWordsResult) {
        this.deleteStopWordsResult = deleteStopWordsResult;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
