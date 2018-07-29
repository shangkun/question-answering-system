package cn.ken.questionansweringsystem.memorydb;

import cn.ken.questionansweringsystem.common.Constant;
import cn.ken.questionansweringsystem.model.knowledge.*;
import cn.ken.questionansweringsystem.utils.Base;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.dictionary.StringDictionary;
import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.seg.Other.CommonAhoCorasickDoubleArrayTrieSegment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.suggest.Suggester;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * author: shangkun <br/>
 * date: 2018/7/26 <br/>
 * what: KnowledgeDB <br/>
 */
public class KnowledgeDB extends Base{
    /**
     * 知识
     */
    public static Map<String,Knowledge> knowledgeMap = new ConcurrentHashMap<>();
    /**
     * 寒暄知识
     */
    public static Map<String,Greeting> greetingMap = new ConcurrentHashMap<>();
    /**
     * 推荐器
     */
    public static Suggester suggester = new Suggester();
    /**
     * 拼音
     */
    public static TreeMap<String, Set<String>> map = new TreeMap<>();
    /**
     * 拼音分词器
     */
    public static  CommonAhoCorasickDoubleArrayTrieSegment<Set<String>> pinYinSegment;

    /**
     * 初始化拼音
     */
    public static void initPinYinMap(){
        StringDictionary dictionary = new StringDictionary("=");
        dictionary.load(HanLP.Config.PinyinDictionaryPath);
        for (Map.Entry<String, String> entry : dictionary.entrySet())
        {
            String pinyins = entry.getValue().replaceAll("[\\d,]", "");
            Set<String> words = map.get(pinyins);
            if (words == null)
            {
                words = new TreeSet<>();
                map.put(pinyins, words);
            }
            words.add(entry.getKey());
        }
        pinYinSegment = new CommonAhoCorasickDoubleArrayTrieSegment<>(map);
    }

    /**
     * 初始化知识
     * @param knowledgeList
     */
    public static void initKnowledge(List<Knowledge> knowledgeList){
        for(Knowledge knowledge:knowledgeList){
            try {
                if(CollectionUtils.isEmpty(knowledge.getAnswerList())){
                    continue;
                }
                List<Term> termList = HanLP.segment(knowledge.getTitle());
                CoreStopWordDictionary.apply(termList);
                knowledge.setTermList(termList);
                suggester.addSentence(knowledge.getTitle());
                if(CollectionUtils.isEmpty(knowledge.getExtensionQuestionList())){
                    for(ExtensionQuestion extensionQuestion:knowledge.getExtensionQuestionList()){
                        List<Term> termList1 = HanLP.segment(knowledge.getTitle());
                        CoreStopWordDictionary.apply(termList1);
                        extensionQuestion.setTermList(termList1);
                        suggester.addSentence(extensionQuestion.getTitle());
                    }
                }
                List<Answer> answerList = knowledge.getAnswerList();
                for(Answer answer:answerList){
                    List<String> summary = HanLP.extractSummary(answer.getContent(), Constant.MAX_SUMMARY_NUMBER);
                    answer.setSummaryList(summary);
                }
                knowledgeMap.put(knowledge.getId(),knowledge);
            } catch (Exception e) {
                continue;
            }
        }
    }

    public static void initGreeting(List<Greeting> greetingList){
        for(Greeting greeting:greetingList){
            try {
                if(CollectionUtils.isEmpty(greeting.getGreetingAnswerList())){
                    continue;
                }
                List<Term> termList = HanLP.segment(greeting.getTitle());
                CoreStopWordDictionary.apply(termList);
                greeting.setTermList(termList);
                suggester.addSentence(greeting.getTitle());
                if(CollectionUtils.isEmpty(greeting.getGreetingExtensionQuestionList())){
                    for(GreetingExtensionQuestion extensionQuestion:greeting.getGreetingExtensionQuestionList()){
                        List<Term> termList1 = HanLP.segment(greeting.getTitle());
                        CoreStopWordDictionary.apply(termList1);
                        extensionQuestion.setTermList(termList1);
                        suggester.addSentence(extensionQuestion.getTitle());
                    }
                }
                greetingMap.put(greeting.getId(),greeting);
            } catch (Exception e) {
                continue;
            }
        }
    }

    public static void initSingleKnowledge(Knowledge knowledge){
        try {
            if(CollectionUtils.isEmpty(knowledge.getAnswerList())){
                return;
            }
            suggester.addSentence(knowledge.getTitle());
            List<Term> termList = HanLP.segment(knowledge.getTitle());
            CoreStopWordDictionary.apply(termList);
            knowledge.setTermList(termList);
            if(CollectionUtils.isEmpty(knowledge.getExtensionQuestionList())){
                for(ExtensionQuestion extensionQuestion:knowledge.getExtensionQuestionList()){
                    List<Term> termList1 = HanLP.segment(knowledge.getTitle());
                    CoreStopWordDictionary.apply(termList1);
                    extensionQuestion.setTermList(termList1);
                    suggester.addSentence(extensionQuestion.getTitle());
                }
            }
            List<Answer> answerList = knowledge.getAnswerList();
            for(Answer answer:answerList){
                List<String> summary = HanLP.extractSummary(answer.getContent(), Constant.MAX_SUMMARY_NUMBER);
                answer.setSummaryList(summary);
            }
            knowledgeMap.put(knowledge.getId(),knowledge);
        } catch (Exception e) {
            return;
        }
    }

    public static void initSingleGreeting(Greeting greeting){
        try {
            if(CollectionUtils.isEmpty(greeting.getGreetingAnswerList())){
                return;
            }
            List<Term> termList = HanLP.segment(greeting.getTitle());
            CoreStopWordDictionary.apply(termList);
            greeting.setTermList(termList);
            suggester.addSentence(greeting.getTitle());
            if(CollectionUtils.isEmpty(greeting.getGreetingExtensionQuestionList())){
                for(GreetingExtensionQuestion extensionQuestion:greeting.getGreetingExtensionQuestionList()){
                    List<Term> termList1 = HanLP.segment(greeting.getTitle());
                    CoreStopWordDictionary.apply(termList1);
                    extensionQuestion.setTermList(termList1);
                    suggester.addSentence(extensionQuestion.getTitle());
                }
            }
            greetingMap.put(greeting.getId(),greeting);
        } catch (Exception e) {
            return;
        }
    }
}
