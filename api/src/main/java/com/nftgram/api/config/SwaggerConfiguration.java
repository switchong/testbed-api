package com.nftgram.api.config;



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
public class SwaggerConfiguration {



    @Bean
    public Docket SwaggerApi() {
        return  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(swaggerInfo()) // API 및 작성자 정보 매핑
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.nftgram.api"))
                .paths(PathSelectors.any()) // api package 전부
                .build()
                .useDefaultResponseMessages(false); // 기본 세팅값을 사용하지 않는다 200, 401 , 402
    }

    private ApiInfo swaggerInfo() {

        return  new ApiInfoBuilder().title("SpringBoot Api Documentation")
                .description("test")
                .license("nftgram")
                .version("1")
                .build();
    }
}
