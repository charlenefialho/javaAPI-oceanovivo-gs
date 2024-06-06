package com.ocenanovivo.oceanovivo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
	@Bean
	OpenAPI customAPI() {
		return new OpenAPI().info(new Info()
				.title("Projeto OceanoVivo")
				.description("Aplicação Java com Spring Boot de solução para a global solution e\r\n"
						+ "		blueFuture que visa a conservação do ecossistema marinho")
				.version("1.0.0")
				.license(new License().name("OceanoVivo 1.0").url("www.google.com")));
	}

}
