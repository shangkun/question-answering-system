package cn.ken.question.answering.system.service.knowledge;

import cn.ken.question.answering.system.JUnit4BaseConfig;
import cn.ken.question.answering.system.common.*;
import cn.ken.question.answering.system.common.Enum;
import cn.ken.question.answering.system.mapper.knowledge.LemmaMapper;
import cn.ken.question.answering.system.model.knowledge.*;
import cn.ken.question.answering.system.model.response.PageData;
import cn.ken.question.answering.system.utils.excel.ExcelReader;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * author: shangkun <br/>
 * date: 2018/7/27 <br/>
 * what: 词库测试 <br/>
 */
public class TestLexiconService extends JUnit4BaseConfig {
    @Autowired
    private LexiconService lexiconService;
    @Autowired
    private LemmaMapper lemmaMapper;

    @Test
    public void test() throws Exception{
        Set<String> stringSet = new HashSet<>();
        int[] arr = {0,1};
        ArrayList<ArrayList<String>> arrayLists = ExcelReader.xlsxReader("F:\\desktop\\backup\\计算机科学.xlsx", arr);
        for(ArrayList<String> arrayList:arrayLists){
            stringSet.add(arrayList.get(0));
        }
        for (String string:stringSet){
            Lexicon request = new Lexicon();
            request.setModifierId("538559372305891328");
            request.setModifyTime(new Date());
            request.setStatus(1);
            request.setTopic(string);
            request.setTopicSet(string);
            request.setType(Enum.businessWord.getValue());
            lexiconService.add(request);
        }
        LexiconRequest lexiconRequest = new LexiconRequest();
        lexiconRequest.setIndex(0);
        lexiconRequest.setPageSize(10);
        PageData pageData = lexiconService.getByAttribute(lexiconRequest);
        pageData.getData();
    }

    @Test
    public void lemma() throws Exception{
        List<Lemma> list = lemmaMapper.selectByPage(0,lemmaMapper.count());
        for (Lemma lemma:list){
            Lexicon request = new Lexicon();
            request.setModifierId("538559372305891328");
            request.setModifyTime(new Date());
            request.setStatus(1);
            request.setTopic(lemma.getLemmaTitle());
            request.setTopicSet(lemma.getLemmaTitle());
            request.setType(Enum.businessWord.getValue());
            lexiconService.add(request);
        }
        LexiconRequest lexiconRequest = new LexiconRequest();
        lexiconRequest.setIndex(0);
        lexiconRequest.setPageSize(10);
        PageData pageData = lexiconService.getByAttribute(lexiconRequest);
        pageData.getData();
    }
}

