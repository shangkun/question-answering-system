package cn.ken.question.answering.system.service.log;

import cn.ken.question.answering.system.mapper.log.LogQaMapper;
import cn.ken.question.answering.system.mapper.log.LogQaRecommendMapper;
import cn.ken.question.answering.system.model.log.LogQaRecommend;
import cn.ken.question.answering.system.model.qa.QuestionAnswer;
import cn.ken.question.answering.system.service.BaseService;
import cn.ken.question.answering.system.utils.Base;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/30 <br/>
 * what: LogServiceImpl <br/>
 */
@Service
public class LogServiceImpl extends Base implements LogService{
    @Autowired
    private LogQaMapper logQaMapper;
    @Autowired
    private LogQaRecommendMapper logQaRecommendMapper;
    @Autowired
    private BaseService baseService;

    @Async
    @Override
    public void add(QuestionAnswer questionAnswer) {
        questionAnswer.setCreateTime(new Date());
        logQaMapper.insert(questionAnswer);
        if(CollectionUtils.isEmpty(questionAnswer.getRecommendList())){
            return;
        }
        logQaRecommendMapper.batchInsert(questionAnswer.getRecommendList());
    }

    @Override
    public int count(String startTime, String endTime) throws Exception {
        return logQaMapper.count(startTime,endTime);
    }

    @Async
    @Override
    public void batchAdd(List<QuestionAnswer> questionAnswerList) throws Exception{
        List<LogQaRecommend> recommendList = new ArrayList<>();
        for(QuestionAnswer questionAnswer:questionAnswerList){
            questionAnswer.setCreateTime(new Date());
            if(CollectionUtils.isEmpty(questionAnswer.getRecommendList())){
                continue;
            }
            recommendList.addAll(questionAnswer.getRecommendList());
        }
        baseService.batchAddLogQa(questionAnswerList);
        baseService.batchAddLogQaRecommend(recommendList);
    }
}
