package cn.ken.question.answering.system.service.knowledge;

import cn.ken.question.answering.system.JUnit4BaseConfig;
import cn.ken.question.answering.system.model.knowledge.GreetingAnswer;
import cn.ken.question.answering.system.model.response.PageData;
import cn.ken.question.answering.system.utils.excel.ExcelReader;
import cn.ken.question.answering.system.common.Enum;
import cn.ken.question.answering.system.model.knowledge.GreetingRequest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: shangkun <br/>
 * date: 2018/7/27 <br/>
 * what: 寒暄知识测试 <br/>
 */
public class TestGreetingService extends JUnit4BaseConfig {
    @Autowired
    private GreetingService greetingService;

    @Test
    public void test() throws Exception{
        Map<String,String> map = new HashMap<>();

        String excelUrl = "E:\\download1\\1616.xlsx";

        int[] arr = {0,1};
        ArrayList<ArrayList<String>> arrayLists = ExcelReader.xlsxReader(excelUrl, arr);
        for(ArrayList<String> arrayList:arrayLists){
            map.put(arrayList.get(0),arrayList.get(1));
        }

        List<GreetingRequest> greetingRequestList = new ArrayList<>();

        for (Map.Entry<String,String> entry:map.entrySet()){
            GreetingRequest request = new GreetingRequest();
            request.setTitle(entry.getKey());
            request.setModifierId("538559372305891328");
            List<GreetingAnswer> answerList = new ArrayList<>();
            GreetingAnswer answer = new GreetingAnswer();
            answer.setAnswer(entry.getValue());
            answer.setChannelId(Enum.allChannel.getValue());
            answerList.add(answer);
            request.setGreetingAnswerList(answerList);
            greetingRequestList.add(request);
        }

        String result = greetingService.batchAdd(greetingRequestList);

        GreetingRequest greetingRequest = new GreetingRequest();
        greetingRequest.setIndex(0);
        greetingRequest.setPageSize(10);
        PageData pageData = greetingService.get(greetingRequest);
        pageData.getData();
    }
}

