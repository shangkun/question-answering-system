package cn.ken.questionansweringsystem;

import cn.ken.questionansweringsystem.utils.Base;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: JUnit4BaseConfig <br/>
 */
@RunWith(JUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextConfiguration
        (
                locations = {
                        "classpath*:config/applicationContext-dao.xml",
                        "classpath*:config/applicationContext-mvc.xml"
                }
        )
public class JUnit4BaseConfig extends Base{
    @Autowired
    public WebApplicationContext wac;

    public MockMvc mockMvc;
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );

    @Before
    public void setup() {
        // 构造appcontext
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        // 初始化Mock
        MockitoAnnotations.initMocks(this);
    }
}
