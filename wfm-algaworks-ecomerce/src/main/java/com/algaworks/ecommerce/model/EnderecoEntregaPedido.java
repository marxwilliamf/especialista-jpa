package com.algaworks.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//Não precisa equals e hash code, por que é uma classe embeddable
@Embeddable
public class EnderecoEntregaPedido {

	//@Column(name = "end_cep") //posso anotar como em uma classe normal
	@Column(length = 9)
	private String cep;
	
	@Column(length = 100)
	private String logradouro;
	
	@Column(length = 10)
	private String numero;
	
	@Column(length = 50)
	private String complemento;
	
	@Column(length = 50)
	private String bairro;
	
	@Column(length = 50)
	private String cidade;
	
	@Column(length = 2)
	private String estado;
	
}
