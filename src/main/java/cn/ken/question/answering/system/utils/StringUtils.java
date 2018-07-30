package cn.ken.question.answering.system.utils;

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
     * 判断是否符合传入的正则
     * @param regular
     * @param str
     * @return
     */
    public static boolean regular(String regular,String str) {
        return Pattern.compile(regular).matcher(str).find();
    }

}
