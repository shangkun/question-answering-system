package cn.ken.questionansweringsystem.memorydb;

import cn.ken.questionansweringsystem.common.Constant;
import cn.ken.questionansweringsystem.model.configuration.Configuration;
import cn.ken.questionansweringsystem.model.configuration.SensitiveWord;
import cn.ken.questionansweringsystem.utils.Base;
import cn.ken.questionansweringsystem.utils.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * author: shangkun <br/>
 * date: 2018/7/26 <br/>
 * what: ConfigurationDB <br/>
 */
public class ConfigurationDB extends Base{

    public static Configuration configuration = null;

    public static Map<String,Set<String>> sensitiveWordMap = new ConcurrentHashMap<>();

    public static void putAllSensitiveWord(List<SensitiveWord> sensitiveWords){
        for(SensitiveWord sensitiveWord:sensitiveWords){
            if(StringUtils.isEmpty(sensitiveWord.getTopic()) ||
                    StringUtils.isEmpty(sensitiveWord.getTopicSet())){
                continue;
            }
            String[] topicArray = sensitiveWord.getTopicSet().split(Constant.TOPIC_SPLITER);
            Set<String> topicSet = new HashSet<>(Arrays.asList(topicArray));
            sensitiveWordMap.put(sensitiveWord.getTopic(),topicSet);
        }
    }

    public static void putOneSensitiveWord(SensitiveWord sensitiveWord){
        if(StringUtils.isEmpty(sensitiveWord.getTopic()) ||
                StringUtils.isEmpty(sensitiveWord.getTopicSet())){
            return;
        }
        String[] topicArray = sensitiveWord.getTopicSet().split(Constant.TOPIC_SPLITER);
        Set<String> topicSet = new HashSet<>(Arrays.asList(topicArray));
        sensitiveWordMap.put(sensitiveWord.getTopic(),topicSet);
    }

    public static String isSensitiveWord(String content){
        for(Map.Entry<String,Set<String>> entry:sensitiveWordMap.entrySet()){
            Set<String> topicSet = entry.getValue();
            for(String topic:topicSet){
                if(content.contains(topic)){
                    return topic;
                }
            }
        }
        return null;
    }
}
