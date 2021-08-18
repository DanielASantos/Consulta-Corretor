package br.com.daniel.ofertaseguros.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.daniel.ofertaseguros.controller.vo.CorretorVO;
import br.com.daniel.ofertaseguros.model.Corretor;
import br.com.daniel.ofertaseguros.service.CorretorService;

@RestController
public class CorretorController {

	private static Logger logger = LoggerFactory.getLogger(CorretorController.class);

	@Autowired
	private CorretorService corretorService;

	@GetMapping("/corretor/{documento}")
	public ResponseEntity<CorretorVO> obterCorretor(@PathVariable Long documento) {

		Optional<Corretor> corretor = corretorService.obterPorCodigo(documento);
		
		if (corretor.isPresent() && corretor.get().getActive()) {
			logger.info("Corretor com documento: " + documento + " ativo");
			return ResponseEntity.ok(CorretorVO.converte(corretor.get()));
			
		} else {
			logger.info("Corretor com documento: " + documento + " está inativo");
			return ResponseEntity.notFound().build();
		}
	}
}
