package br.com.daniel.ofertaseguros.controller.vo;

import br.com.daniel.ofertaseguros.model.Corretor;
import lombok.Getter;

@Getter
public class CorretorVO {

	private String nome;
	private String documento;
	private String codigo;
	private String dataCriacao;
	private Double TaxaComissao;
	private Boolean flagAtivo;

	public CorretorVO(Corretor corretor) {
		this.nome = corretor.getName();
		this.documento = corretor.getDocument();
		this.codigo = corretor.getCode();
		this.dataCriacao = corretor.getCreateDate();
		this.TaxaComissao = corretor.getCommissionRate();
		this.flagAtivo = corretor.getActive();
	}

	public static CorretorVO converte(Corretor corretor) {
		return new CorretorVO(corretor);
	}

}
