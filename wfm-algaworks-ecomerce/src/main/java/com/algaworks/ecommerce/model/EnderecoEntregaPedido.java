package com.algaworks.ecommerce.model;

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
	private String cep;
	
	private String logradouro;
	
	private String numero;
	
	private String complemento;
	
	private String bairro;
	
	private String cidade;
	
	private String estado;
	
}
