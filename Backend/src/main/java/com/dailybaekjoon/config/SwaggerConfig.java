package com.dailybaekjoon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
  @Bean
  public Docket SwaggerApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(swaggerInfo()) //  API Docu 및 작성자 정보 매핑
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.dailybaekjoon.controller"))
        .paths(PathSelectors.any()) //  controller package 전부
        .build()
        .useDefaultResponseMessages(false); //  기본 세팅값인 200, 401, 402, 403, 404를 사용하지 않는다.
  }

  private ApiInfo swaggerInfo() {
    return new ApiInfoBuilder().title("dailybaekjoon API Documentation")
        .description("앱 서버 API를 설명하기 위한 문서입니다.")
        .version("1")
        .build();
  }
}