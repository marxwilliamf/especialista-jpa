package com.algaworks.ecommerce.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoId implements Serializable { //precisa implementasr serializable para fazer chave composta

    @EqualsAndHashCode.Include
	private Integer pedidoId;
    
    @EqualsAndHashCode.Include
    private Integer  produtoId;
	
}
