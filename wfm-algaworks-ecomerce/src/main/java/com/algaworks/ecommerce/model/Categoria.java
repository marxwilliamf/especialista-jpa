package com.algaworks.ecommerce.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="categoria", uniqueConstraints = @UniqueConstraint(name = "unq_nome", columnNames = "nome"), 
		indexes = @Index(name = "idx_nome", columnList = "nome"))
public class Categoria extends EntidadeBaseInteger{
    
	@Column(length = 100, nullable = false) //varchar(100) not null
    private String nome;     
    
    @ManyToOne
    @JoinColumn(name = "categoria_pai_id")
    private Categoria categoriaPai;
    
    @OneToMany(mappedBy = "categoriaPai")
    private List<Categoria> categorias;

    @ManyToMany(mappedBy = "categorias")
    private List<Produto> produtos;
    
    
    public Categoria() {
    	
    }
    
    public Categoria(String nome, Categoria categoriaPai) {
        this.nome = nome;
        this.categoriaPai = categoriaPai;
    }
}
