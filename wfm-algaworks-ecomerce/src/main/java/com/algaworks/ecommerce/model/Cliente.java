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
public class Cliente {

    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    private String nome;

    public Cliente() {} //pra funcionar com o JPA precisa ter um cosntrotor vazio

    public Cliente(Integer id) {
        this.id = id;
    }

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}
