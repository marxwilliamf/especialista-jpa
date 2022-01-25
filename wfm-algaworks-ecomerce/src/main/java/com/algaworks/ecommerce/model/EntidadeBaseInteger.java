package com.algaworks.ecommerce.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@MappedSuperclass
public class EntidadeBaseInteger {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //usa a função auto incremento do banco de dados
	@EqualsAndHashCode.Include
    public Integer id;
	
}

//@MappedSuperClass //caso não foce declarado essa característica não poderia ser excluido a declaração do id da classe que erdasse
