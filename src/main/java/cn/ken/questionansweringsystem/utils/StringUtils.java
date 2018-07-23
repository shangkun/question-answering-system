package cn.ken.questionansweringsystem.utils;

import java.util.Locale;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: StringUtils <br/>
 */
public class StringUtils {
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
}
