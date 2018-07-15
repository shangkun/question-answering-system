package cn.ken.questionansweringsystem.utils.hanlp;

import cn.ken.questionansweringsystem.common.PartOfSpeech;
import cn.ken.questionansweringsystem.utils.StringUtils;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLWord;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.Dijkstra.DijkstraSegment;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.suggest.Suggester;
import com.hankcs.hanlp.tokenizer.IndexTokenizer;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.SpeedTokenizer;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: HanlpUtils <br/>
 */
public class HanlpUtils {

    public static void main(String[] args){
        String string = "说明本文主要论述的问题和自己的工作，注明是实现别人的算法，还是有自己的改进工作，如果是别人没有做过的工作，要在此强调一下，如果是实现某种算法和系统，没有照搬别人的想法，即使有现成的系统，也是很好的工作，因此也要注明，同时，要论述一下对论文工作的进一步考虑";
        List<Term> termList = NLPTokenizer.segment(string);
        for (Term term:termList){
            System.out.println(term.word+":"+ PartOfSpeech.getValue(term.nature.toString()));
        }
//        System.out.println(termList);
//        System.out.println(HanLP.extractKeyword(string,1));
//        System.out.println(HanLP.extractPhrase(string,1));
//        System.out.println(HanLP.extractSummary(string,1));
//        System.out.println(HanLP.extractWords(string,1));
//        System.out.println(HanLP.convertToPinyinList(string));
//        System.out.println(HanLP.s2t(string));
//        Segment nShortSegment = new NShortSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
//        Segment shortestSegment = new DijkstraSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
//        String[] testCase = new String[]{
//                "今天，刘志军案的关键人物,山西女商人丁书苗在市二中院出庭受审。",
//                "刘喜杰石国祥会见吴亚琴先进事迹报告团成员",
//        };
//        for (String sentence : testCase)
//        {
//            System.out.println("N-最短分词：" + nShortSegment.seg(sentence) + "\n最短路分词：" + shortestSegment.seg(sentence));
//        }        // 动态增加
//        CustomDictionary.add("攻城狮");
//        // 强行插入
//        CustomDictionary.insert("白富美", "nz 1024");
//        // 删除词语（注释掉试试）
////        CustomDictionary.remove("攻城狮");
//        System.out.println(CustomDictionary.add("单身狗", "nz 1024 n 1"));
//        System.out.println(CustomDictionary.get("单身狗"));
//
//        String text = "攻城狮逆袭单身狗，迎娶白富美，走上人生巅峰";  // 怎么可能噗哈哈！
//
//        // AhoCorasickDoubleArrayTrie自动机扫描文本中出现的自定义词语
//        final char[] charArray = text.toCharArray();
//        CustomDictionary.parseText(charArray, new AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute>()
//        {
//            @Override
//            public void hit(int begin, int end, CoreDictionary.Attribute value)
//            {
//                System.out.printf("[%d:%d]=%s %s\n", begin, end, new String(charArray, begin, end - begin), value);
//            }
//        });
//
//        // 自定义词典在所有分词器中都有效
//        System.out.println(HanLP.segment(string));

//        String content = "程序员(英文Programmer)是从事程序开发、维护的专业人员。一般将程序员分为程序设计人员和程序编码人员，但两者的界限并不非常清楚，特别是在中国。软件从业人员分为初级程序员、高级程序员、系统分析员和项目经理四大类。";
//        List<String> keywordList = HanLP.extractPhrase(string, 3);
//        System.out.println(keywordList);

        //根据已有文本序列 给出推荐
//        Suggester suggester = new Suggester();
//        String[] titleArray =
//                (
//                        "威廉王子发表演说 呼吁保护野生动物\n" +
//                                "《时代》年度人物最终入围名单出炉 普京马云入选\n" +
//                                "“黑格比”横扫菲：菲吸取“海燕”经验及早疏散\n" +
//                                "日本保密法将正式生效 日媒指其损害国民知情权\n" +
//                                "英报告说空气污染带来“公共健康危机”"
//                ).split("\\n");
//        for (String title : titleArray)
//        {
//            suggester.addSentence(title);
//        }
//
//        System.out.println(suggester.suggest("发言", 1));       // 语义
//        System.out.println(suggester.suggest("危机公共", 1));   // 字符
//        System.out.println(suggester.suggest("mayun", 1));      // 拼音

//        String text = "你是好人";
//        String query = "你是坏人";
//        System.out.println(org.apache.commons.lang3.StringUtils.getJaroWinklerDistance(text,query));

        //
        CoNLLSentence sentence = HanLP.parseDependency(string);
        System.out.println(sentence);
        // 可以方便地遍历它
        for (CoNLLWord word : sentence)
        {
            System.out.printf("%s --(%s)--> %s\n", word.LEMMA, word.DEPREL, word.HEAD.LEMMA);
        }
        // 也可以直接拿到数组，任意顺序或逆序遍历
        CoNLLWord[] wordArray = sentence.getWordArray();
        for (int i = wordArray.length - 1; i >= 0; i--)
        {
            CoNLLWord word = wordArray[i];
            System.out.printf("%s --(%s)--> %s\n", word.LEMMA, word.DEPREL, word.HEAD.LEMMA);
        }
        // 还可以直接遍历子树，从某棵子树的某个节点一路遍历到虚根
        CoNLLWord head = wordArray[12];
        while ((head = head.HEAD) != null)
        {
            if (head == CoNLLWord.ROOT) System.out.println(head.LEMMA);
            else System.out.printf("%s --(%s)--> ", head.LEMMA, head.DEPREL);
        }
    }

    public static List<Term> segment(String string){
        if(StringUtils.isEmpty(string)){
            return Collections.emptyList();
        }
        return HanLP.segment(string);
    }

    public static void removeCustomDictionary(String word){
        if(StringUtils.isEmpty(word)){
            return;
        }
        CustomDictionary.remove(word);
    }

    public static boolean addCustomDictionary(String word){
        if(StringUtils.isEmpty(word)){
            return false;
        }
        return CustomDictionary.add(word);
    }

    public static boolean insertCustomDictionary(String word){
        if(StringUtils.isEmpty(word)){
            return false;
        }
        return CustomDictionary.insert(word);
    }

    public static boolean addCustomDictionary(String word, String natureWithFrequency){
        if(StringUtils.isEmpty(word) || StringUtils.isEmpty(natureWithFrequency)){
            return false;
        }
        return CustomDictionary.add(word,natureWithFrequency);
    }

    public static boolean insertCustomDictionary(String word, String natureWithFrequency){
        if(StringUtils.isEmpty(word) || StringUtils.isEmpty(natureWithFrequency)){
            return false;
        }
        return CustomDictionary.insert(word, natureWithFrequency);
    }
}
