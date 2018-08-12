package cn.ken.question.answering.system.service.knowledge;

import cn.ken.question.answering.system.JUnit4BaseConfig;
import cn.ken.question.answering.system.mapper.knowledge.LemmaMapper;
import cn.ken.question.answering.system.model.knowledge.Lemma;
import cn.ken.question.answering.system.model.knowledge.LemmaList;
import cn.ken.question.answering.system.utils.StringUtils;
import cn.ken.question.answering.system.utils.TextFileReader;
import com.google.gson.JsonSyntaxException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by reals on 2018/8/12.
 */
public class TestLemmaMapper extends JUnit4BaseConfig{

    @Autowired
    private LemmaMapper lemmaMapper;

    @Test
    public void test(){

        for(int i=0;i<=100;i++){
            String string = TextFileReader.readByLines("C:\\Users\\reals\\Desktop\\work\\" + i + ".txt");
            if(StringUtils.isEmpty(string)){
                continue;
            }
            try {
                LemmaList lemmaList = gson.fromJson(string,LemmaList.class);
                List<Lemma> list = lemmaList.getLemmaList();
                if(CollectionUtils.isEmpty(list)){
                    continue;
                }
                lemmaMapper.batchInsert(list);
            } catch (JsonSyntaxException e) {
                logger.error(e.getMessage(),e);
            }

        }
    }
}
