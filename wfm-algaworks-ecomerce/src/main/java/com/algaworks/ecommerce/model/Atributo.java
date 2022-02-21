package com.algaworks.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Atributo {
	
	//@Column(name = "atr_nome") // caso queira personalizar o nome
	@Column(length = 100, nullable = false) //varrchar(100) not null
	private String nome;
	
	private String valor;
	
}
