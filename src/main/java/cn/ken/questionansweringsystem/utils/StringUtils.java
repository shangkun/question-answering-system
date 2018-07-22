package cn.ken.questionansweringsystem.utils;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: StringUtils <br/>
 */
public class StringUtils {

    public static boolean isEmpty(String string){
        return StringUtils.isEmpty(string);
    }

    public static boolean lengthCheck(String string,int min,int max){
        if(isEmpty(string)){
            return false;
        }
        if(string.length()<min || string.length()>max){
            return false;
        }
        return true;
    }
}
