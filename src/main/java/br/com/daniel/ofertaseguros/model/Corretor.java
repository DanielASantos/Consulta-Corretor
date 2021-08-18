package br.com.daniel.ofertaseguros.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Corretor {

	private String code;
	private String name;
	private String document;
	private String createDate;
	private Double commissionRate;
	private Boolean active;
	
}
