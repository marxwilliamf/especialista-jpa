package com.algaworks.ecommerce.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoId implements Serializable { //precisa implementasr serializable para fazer chave composta
	
	@EqualsAndHashCode.Include
	@Column(name = "pedido_id") //agora com as anotações do JPA
	private Integer pedidoId;
	    
    @EqualsAndHashCode.Include
    @Column(name = "produto_id")
    private Integer  produtoId;
	
}
