package br.com.daniel.ofertaseguros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class OfertaSegurosApplication {

	private static Logger logger = LoggerFactory.getLogger(OfertaSegurosApplication.class);

	@Bean
	public WebClient webClient(WebClient.Builder builder) {
		return builder
			.baseUrl("https://607732991ed0ae0017d6a9b0.mockapi.io/insurance/v1/")
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.build();
	}
	
	public static void main(String[] args) {
		
		logger.info("Iniciando a API de consulta de corretor");
		
		SpringApplication.run(OfertaSegurosApplication.class, args);
		
		logger.info("API de consulta de corretor iniciada e printa para receber requisições");
	}

}
