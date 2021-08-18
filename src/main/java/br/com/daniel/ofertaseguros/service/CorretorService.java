package br.com.daniel.ofertaseguros.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.daniel.ofertaseguros.model.Corretor;
import reactor.core.publisher.Mono;

@Service
public class CorretorService {
	
	private static Logger logger = LoggerFactory.getLogger(CorretorService.class);

	@Autowired
	private WebClient webClient;

	@SuppressWarnings("finally")
	public Optional <Corretor> obterPorCodigo(Long documento) {
		
		Corretor corretor = null;		
		try {
			Mono<Corretor> monoCorretor = this.webClient
				.method(HttpMethod.GET)
				.uri("/broker/{codigo}", documento)
				.retrieve()
				.bodyToMono(Corretor.class);
	
			corretor = monoCorretor.block();
			logger.info("Consulta da API dos dados do corretor realizada com sucesso.");
			
			Mono<Corretor> monoDetalhes = this.webClient
				.method(HttpMethod.GET)
				.uri("/brokerData/{codigo}", corretor.getCode())
				.retrieve()
				.bodyToMono(Corretor.class);
			
			Corretor detalhes = monoDetalhes.block();
			logger.info("Consulta da API dos detalhes do corretor realizada com sucesso.");
			
			corretor.setCommissionRate(detalhes.getCommissionRate());
			corretor.setActive(detalhes.getActive());
			
			logger.info("Corretor com documento: " + documento + " encontrado");
			
		} catch (Exception e) {
			logger.info("Corretor com documento: " + documento + " não encontrado");
			
		} finally {
			return Optional.ofNullable(corretor);
		}
	}
}
