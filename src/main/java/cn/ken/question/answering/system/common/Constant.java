package cn.ken.question.answering.system.common;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: 常量 <br/>
 */
public class Constant {

    public static String ID;

    public static double upperLimitThreshold = 0.8;

    public static double lowerLimitThreshold = 0.6;

    public static String unknown;

    public static int recommendKnowledgeNumber;

    public static String[] whiteList = {"validate","login","logout"};

    public static final String printPattern = "=====================";

    public static final int TIMEOUT = 1800;

    public static final int BATCH_NUMBER = 10000;

    public static final int MAX_RETRIEVAL_NUMBER = 10;

    public static final int MAX_SUMMARY_NUMBER = 3;

    public static final int MAX_KEYWORDS_NUMBER = 3;

    public static final int MAX_NEW_WORD_NUMBER = 3;

    public static final String ACCESS_TOKEN = "access_token";

    public static final String ACCESS_TOKEN_WITH_PREFIX = "access_token:";

    public static final String ERROR_INFO = "异常";

    public static final String SESSION_ID = "session_id:";

    public static final String QA_CONTEXT = "qa_context:";

    public static final String DEFAULT_USER = "538559372305891328";

    public static final String DEFAULT_ROLE = "538945468713926656";

    public static final String TOPIC_SPLITER = ";";

    public static final String ROOT_PID = "0";
}
