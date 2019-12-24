package com.goshine.ptadmui.common.swagger;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * swagger2的配置文件
 * 这里可以配置swagger2的一些基本的内容，比如扫描的包等等
 * @author goshine
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
	@Autowired
	private SwaggerProperties swaggerProperties;
     @Bean
     public Docket createRestApi() {
		 return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
				 // 是否开启
				 .enable(swaggerProperties.getSwaggerEnabled()).select()
				 .apis(basePackage(swaggerProperties.getBasePackages()))
				 .paths(PathSelectors.any()).build();
	  }
	  /**
	   * 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
	   * @return
	   */
	  private ApiInfo apiInfo(){
		  return new ApiInfoBuilder()
				  .title(swaggerProperties.getApiTitle())
				  .version(swaggerProperties.getApiVersion())
				  .description(swaggerProperties.getApiDesc())
				  // 作者信息
				  .contact(new Contact(swaggerProperties.getApiAuthor().get("name"), swaggerProperties.getApiAuthor().get("url"), swaggerProperties.getApiAuthor().get("email")))
				  .build();
	  }
	public  Predicate<RequestHandler> basePackage(final List<String> basePackages) {
		return input -> declaringClass(input).transform(handlerPackage(basePackages)).or(true);
	}

	private static Function<Class<?>, Boolean> handlerPackage(List<String> basePackages)     {
		return input -> {
			// 循环判断匹配
			for (String strPackage : basePackages) {
				boolean isMatch = input.getPackage().getName().startsWith(strPackage);
				if (isMatch) {
					return true;
				}
			}
			return false;
		};
	}

	private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
		return Optional.fromNullable(input.declaringClass());
	}
}
