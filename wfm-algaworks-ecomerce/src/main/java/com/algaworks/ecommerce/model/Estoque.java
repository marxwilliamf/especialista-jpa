package com.algaworks.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="cliente")
public class Estoque {

    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    private Integer produtoId;
    private Integer quantidade;
    

    public Estoque() {} //pra funcionar com o JPA precisa ter um cosntrotor vazio

    public Estoque(Integer id) {
        this.id = id;
    }

    public Estoque(Integer id, String nome, Integer categoriaPaiId) {
        this.id = id;
        this.nome = nome;
        this.categoriaPaiId = categoriaPaiId;
    }

}
