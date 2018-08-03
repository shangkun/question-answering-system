package cn.ken.question.answering.system.utils;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.seg.common.Term;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * author: shangkun <br/>
 * date: 2018/7/19 <br/>
 * what: TextFileReader <br/>
 */
public class TextFileReader{

    private static final String START = "Q:";

    private static final String END = "A:";

    public static void readFileByLines(Map<String,String> map,String fileName){
        File file = new File(fileName);
        if(!file.exists()){
            return;
        }
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
             BufferedReader reader = new BufferedReader(inputStreamReader)){
            String tempString = null;
            String question = "",answer = "";
            int q = 0,a = 0,qPlus = 0,aPlus = 0;
            while ((tempString = reader.readLine()) != null) {
                int qIndex = tempString.indexOf(START);
                if(qIndex>=0){
                    qPlus++;
                    if(qPlus==2 && aPlus==1){
                        answer = removeNumber(answer);
                        map.put(question,answer);
                        qPlus = 1;
                        aPlus = 0;
                        question = "";
                        answer = "";
                    }
                    question+=tempString.substring(qIndex+2,tempString.length());
                    q = 1;
                    a = 0;
                    continue;
                }
                int aIndex = tempString.indexOf(END);
                if(aIndex>=0){
                    aPlus++;
                    answer+=tempString.substring(aIndex+2,tempString.length());
                    a = 1;
                    q = 0;
                    continue;
                }
                if(q == 1){
                    question+=tempString;
                }else if(a == 1){
                    answer+=tempString;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readByLines(Map<String,String> map,String fileName){
        File file = new File(fileName);
        if(!file.exists()){
            return;
        }
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
             BufferedReader reader = new BufferedReader(inputStreamReader)){
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                StringBuilder stringBuilder = new StringBuilder();
//                List<Term> terms = HanLP.segment(tempString);
//                CoreStopWordDictionary.apply(terms);
//                for(Term term:terms){
//                    stringBuilder.append(term.word);
//                }
                List<String> terms = HanLP.extractKeyword(tempString,3);
                for(String term:terms){
                    stringBuilder.append(term);
                }
                map.put(tempString,stringBuilder.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String removeNumber(String string){
        String three = string.substring(string.length()-3,string.length());
        if(StringUtils.regular(StringUtils.INT_REGULAR,three)){
            return string.substring(0,string.length()-3);
        }
        String two = string.substring(string.length()-2,string.length());
        if(StringUtils.regular(StringUtils.INT_REGULAR,two)){
            return string.substring(0,string.length()-2);
        }
        String one = string.substring(string.length()-1,string.length());
        if(StringUtils.regular(StringUtils.INT_REGULAR,one)){
            return string.substring(0,string.length()-1);
        }
        return string;
    }
}
