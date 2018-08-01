package cn.ken.question.answering.system.utils;

import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: StringUtils <br/>
 */
public class StringUtils {

    public static final String INT_REGULAR = "^-?[1-9]\\d*$";

    /**
     * 为空判断
     * @param string
     * @return
     */
    public static boolean isEmpty(String string){
        return org.springframework.util.StringUtils.isEmpty(string);
    }

    /**
     * 长度判断
     * @param string
     * @param min
     * @param max
     * @return
     */
    public static boolean lengthCheck(String string,int min,int max){
        if(isEmpty(string)){
            return false;
        }
        if(string.length()<min || string.length()>max){
            return false;
        }
        return true;
    }

    /**
     * 转换小写
     * @param str
     * @return
     */
    public static String toLowerCaseLocal(String str){
        if (str!=null) {
            return str.toLowerCase(Locale.US);
        }else{
            return null;
        }
    }

    /**
     * 获取Jaro-Winkler编辑距离
     * @param str1
     * @param str2
     * @return
     */
    public static double getJaroWinklerDistance(String str1,String str2){
        return org.apache.commons.lang3.StringUtils.getJaroWinklerDistance(str1,str2);
    }

    /**
     * 获取Jaro编辑距离
     * @param first
     * @param second
     * @return
     */
    public static double getJaroDistance(List<Term> first,List<Term> second) {
        return score(first, second);
    }

    /**
     * 获取Jaro编辑距离 最小的匹配单元为HLP的Term
     * @param first
     * @param second
     * @return
     */
    private static double score(List<Term> first, List<Term> second) {
        List<Term> shorter;
        List<Term> longer;
        if(first.size() > second.size()) {
            longer = first;
            shorter = second;
        } else {
            longer = second;
            shorter = first;
        }

        int halflength = shorter.size()/2 +1;
        List<String> m1 = getListOfMatchingTermWithin(shorter, longer, halflength);
        List<String> m2 = getListOfMatchingTermWithin(longer, shorter, halflength);
        if(m1.size() != 0 && m2.size() != 0) {
            if(m1.size() != m2.size()) {
                return 0.0D;
            } else {
                int transpositions = transpositions(m1, m2);
                double dist = ((double)m1.size() / (double)shorter.size() + (double)m2.size() / (double)longer.size() + (double)(m1.size() - transpositions) / (double)m1.size()) / 3.0D;
                return dist;
            }
        } else {
            return 0.0D;
        }
    }

    /**
     * 获取匹配到的Term
     * @param first
     * @param second
     * @param limit
     * @return
     */
    private static List<String> getListOfMatchingTermWithin(List<Term> first, List<Term> second, int limit) {
        List<String> common = new ArrayList<>();
        List<Term> copy = second;

        for(int i = 0; i < first.size(); ++i) {
            Term term = first.get(i);
            boolean found = false;

            for(int j = Math.max(0, i - limit); !found && j < Math.min(i + limit, second.size()); ++j) {
                if(copy.get(j).equals(term)) {
                    found = true;
                    common.add(term.toString());
                }
            }
        }

        return common;
    }

    /**
     * 获取偏移量
     * @param first
     * @param second
     * @return
     */
    private static int transpositions(List<String> first, List<String> second) {
        int transpositions = 0;

        for(int i = 0; i < first.size(); ++i) {
            if(!first.get(i).equals(second.get(i))) {
                ++transpositions;
            }
        }

        return transpositions / 2;
    }


    /**
     * 判断是否符合传入的正则
     * @param regular
     * @param str
     * @return
     */
    public static boolean regular(String regular,String str) {
        return Pattern.compile(regular).matcher(str).find();
    }
}
