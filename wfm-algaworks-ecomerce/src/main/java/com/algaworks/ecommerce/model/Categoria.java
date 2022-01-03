package com.algaworks.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //estratégia que deixa o hibernate escolher como vai gerar o id, executando o teste ele criou a tabela hibernate_sequence
    @EqualsAndHashCode.Include
    private Integer id;
    
    private String nome;
    
    @Column(name = "categoria_pai_id")
    private Integer categoriaPaiId;
    

    public Categoria() {} //pra funcionar com o JPA precisa ter um cosntrutor vazio

    public Categoria(Integer id) {
        this.id = id;
    }

    public Categoria(Integer id, String nome, Integer categoriaPaiId) {
        this.id = id;
        this.nome = nome;
        this.categoriaPaiId = categoriaPaiId;
    }

    
    
}
