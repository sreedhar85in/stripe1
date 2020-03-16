package com.tranxactor.stripe1.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@ComponentScan({ "com.tranxactor.stripe1.controller" })
public class SwaggerConfig implements WebMvcConfigurer {

	@Bean
	public Docket userApi() {
		List<ResponseMessage> list = new java.util.ArrayList<>();
		list.add(new ResponseMessageBuilder().code(500).message("500 message").responseModel(new ModelRef("Result"))
				.build());
		list.add(new ResponseMessageBuilder().code(401).message("Unauthorized").responseModel(new ModelRef("Result"))
				.build());
		list.add(new ResponseMessageBuilder().code(406).message("Not Acceptable").responseModel(new ModelRef("Result"))
				.build());

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.tranxactor.stripe1.controller")).paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Westfield Corporate Stripe API - v1.0").description(
				"This project is a Stripe implementation for the Westfield corporate from the existing provider wincave")
				.version("1.0.0").license("Apache 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
				.build();
	}
}