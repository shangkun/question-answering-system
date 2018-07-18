package cn.ken.questionansweringsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: SwaggerConfig <br/>
 */
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {"cn.ken.questionansweringsystem.controller"})
@EnableWebMvc
public class SwaggerConfig extends WebMvcConfigurationSupport {


    @Bean
    public Docket customDocket() {
        ApiInfo apiInfo = apiInfo();
        Docket docket = new Docket(DocumentationType.SWAGGER_2).select().paths(regex("/api/.*")).build()
                .apiInfo(apiInfo).useDefaultResponseMessages(false);
        return docket;
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("ken", "https://github.com/shangkun/question-answering-system", "https://github.com/shangkun/question-answering-system");
        return new ApiInfo("question-answering-system interface center",//大标题 title
                "interface center",//小标题
                "1.0",//版本
                "https://github.com/shangkun/question-answering-system",//termsOfServiceUrl
                contact,//作者
                "question-answering-system",//链接显示文字
                "https://github.com/shangkun/question-answering-system"//网站链接
        );
    }

}