package cn.ken.questionansweringsystem.controller;

import cn.ken.questionansweringsystem.JUnit4BaseConfig;
import cn.ken.questionansweringsystem.model.User;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: 测试用户管理接口类 <br/>
 */
public class TestUserController extends JUnit4BaseConfig {
    @InjectMocks
    UserController controller;

    @Test
    public void add(){
        try {
            User user = new User();
            user.setId("2");
            user.setAccount("1");
            user.setName("1");
            user.setPassword("1");
            user.setStatus(1);

            String json = gson.toJson(user);
            logger.info(json);
            this.mockMvc
                    .perform(
                            post("/api/user/add")
                                    .contentType(APPLICATION_JSON_UTF8)
                                    .accept(APPLICATION_JSON_UTF8)
                                    .content(json)
                    )
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code", is(200)));
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }
}
