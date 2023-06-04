package com.hbpu.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <b><code>Knife4jConfig</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2023/5/27 22:04.
 *
 * @author 皮
 */
@Configuration
//Knife4j接口文档基于Swagger2，对Swagger2加强了，所以EnableSwagger2是启用Swagger2的相关功能
@EnableSwagger2
public class Knife4jConfig {
    @Bean
    public Docket docket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //apis： 添加swagger接口提取范围，该范围中接口会被Knife4j接口文档测试
                .apis(RequestHandlerSelectors.basePackage("com.hbpu.backend.controller"))
                .paths(PathSelectors.any())
                .build();

        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("康安体检管理系统API")
                .description("codingmore")
                .contact(new Contact("编程大师", "https://www.abc.com", "380430200@qq.com"))
                .version("1.0")
                .build();
    }
}
