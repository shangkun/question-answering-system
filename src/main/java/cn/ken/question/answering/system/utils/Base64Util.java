package cn.ken.question.answering.system.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author: shangkun <br/>
 * date: 2018/8/28 <br/>
 * what: Base64加解密 <br/>
 */
public class Base64Util {

    public static final Logger logger = LoggerFactory.getLogger(Base64Util.class);

    private Base64Util() {
    }

    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String decryptBASE64(String key) {
        byte[] bt;
        try {
            if(StringUtils.isEmpty(key)){
                return "";
            }
            bt = Base64.getDecoder().decode(key);
            return new String(bt,"utf-8");
        } catch (IOException e) {
            logger.info(e.getMessage());
            return "";
        }
    }

    /**
     * BASE64加密
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(String key) {
        try {
            if(StringUtils.isEmpty(key)){
                return "";
            }
            return replaceBlack(Base64.getEncoder().encodeToString(key.getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            logger.info(e.getMessage());
            return "";
        }
    }

    /**
     * @param str
     * @return
     */
    public static String replaceBlack(String str){
        String dest = "";
        if(StringUtils.isEmpty(str)){
            return null;
        }
        Pattern p = Pattern.compile("\\s*|\t|\r|\n|\r\n");
        Matcher m = p.matcher(str);
        dest = m.replaceAll("");
        return dest;
    }
}
