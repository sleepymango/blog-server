package com.sleepymango.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Description Knife4j配置
 * @Author sleepymango
 * @Date 2021/3/23 22:12
 **/

@Configuration
@EnableOpenApi
public class SwaggerConfiguration {
    @Bean
    public Docket createRestApi() {
        // 和v2版本不同的是documentationType 不一样了
        return new Docket(DocumentationType.OAS_30)
                .select()
                // 设置扫描路径
                .apis(RequestHandlerSelectors.basePackage("com.sleepymango.blog.controller"))
                .build();
    }
}
