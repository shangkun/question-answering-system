package cn.ken.questionansweringsystem.model.qa;

import cn.ken.questionansweringsystem.model.LogQa;
import cn.ken.questionansweringsystem.model.LogQaRecommend;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.mining.word.WordInfo;
import com.hankcs.hanlp.seg.common.ResultTerm;
import com.hankcs.hanlp.seg.common.Term;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * author: shangkun <br/>
 * date: 2018/7/25 <br/>
 * what: 问答 <br/>
 */
public class QuestionAnswer extends LogQa{

    private List<Term> segmentResult;

    private String segmentResultToString;

    private List<Term> deleteStopWordsResult;

    private String deleteStopWordsResultToString;

    private String message;

    private List<Retrieval> retrievalList;

    private Retrieval greetingRetrieval;

    private List<LogQaRecommend> recommendList;

    private QuestionAnswerHistory history;
    //提示语
    private String reminders;
    //推荐个数
    private Integer suggestNumber;
    //依存句法分析结果
    private String dependencyParserResult;
    //中文人名识别
    private String chineseNameRecognitionResult;
    //机构名称识别
    private String organizationRecognitionResult;
    //地名识别
    private String placeRecognitionResult;
    //URL识别
    private String URLRecognitionResult;
    //关键词
    private String keyWords;
    //新词发现
    private String newWordDiscover;
    //拼音结果
    private String pinYinResult;

    private boolean debugMode;

    public void setSegmentResultToString(String segmentResultToString) {
        this.segmentResultToString = segmentResultToString;
    }

    public void setDeleteStopWordsResultToString(String deleteStopWordsResultToString) {
        this.deleteStopWordsResultToString = deleteStopWordsResultToString;
    }

    public String getDependencyParserResult() {
        return dependencyParserResult;
    }

    public void setDependencyParserResult(String dependencyParserResult) {
        this.dependencyParserResult = dependencyParserResult;
    }

    public String getChineseNameRecognitionResult() {
        return chineseNameRecognitionResult;
    }

    public void setChineseNameRecognitionResult(String chineseNameRecognitionResult) {
        this.chineseNameRecognitionResult = chineseNameRecognitionResult;
    }

    public String getOrganizationRecognitionResult() {
        return organizationRecognitionResult;
    }

    public void setOrganizationRecognitionResult(String organizationRecognitionResult) {
        this.organizationRecognitionResult = organizationRecognitionResult;
    }

    public String getPlaceRecognitionResult() {
        return placeRecognitionResult;
    }

    public void setPlaceRecognitionResult(String placeRecognitionResult) {
        this.placeRecognitionResult = placeRecognitionResult;
    }

    public String getURLRecognitionResult() {
        return URLRecognitionResult;
    }

    public void setURLRecognitionResult(String URLRecognitionResult) {
        this.URLRecognitionResult = URLRecognitionResult;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getNewWordDiscover() {
        return newWordDiscover;
    }

    public void setNewWordDiscover(String newWordDiscover) {
        this.newWordDiscover = newWordDiscover;
    }

    public String getPinYinResult() {
        return pinYinResult;
    }

    public void setPinYinResult(String pinYinResult) {
        this.pinYinResult = pinYinResult;
    }

    public Integer getSuggestNumber() {
        return suggestNumber;
    }

    public void setSuggestNumber(Integer suggestNumber) {
        this.suggestNumber = suggestNumber;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    public String getReminders() {
        return reminders;
    }

    public void setReminders(String reminders) {
        this.reminders = reminders;
    }

    public Retrieval getGreetingRetrieval() {
        return greetingRetrieval;
    }

    public void setGreetingRetrieval(Retrieval greetingRetrieval) {
        this.greetingRetrieval = greetingRetrieval;
    }

    public QuestionAnswerHistory getHistory() {
        return history;
    }

    public void setHistory(QuestionAnswerHistory history) {
        this.history = history;
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

    public List<Term> getSegmentResult() {
        return segmentResult;
    }

    public void setSegmentResult(List<Term> segmentResult) {
        this.segmentResult = segmentResult;
        if(segmentResult!=null){
            this.segmentResultToString = segmentResult.toString();
        }
    }

    public List<Term> getDeleteStopWordsResult() {
        return deleteStopWordsResult;
    }

    public void setDeleteStopWordsResult(List<Term> deleteStopWordsResult) {
        this.deleteStopWordsResult = deleteStopWordsResult;
        if(deleteStopWordsResult!=null){
            this.deleteStopWordsResultToString = deleteStopWordsResult.toString();
        }
    }

    public String getSegmentResultToString() {
        return segmentResultToString;
    }

    public String getDeleteStopWordsResultToString() {
        return deleteStopWordsResultToString;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
