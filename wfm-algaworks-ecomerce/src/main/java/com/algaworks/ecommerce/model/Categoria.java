package com.algaworks.ecommerce.model;

import javax.persistence.Column;
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
@Table(name="categoria")
public class Categoria {


    @EqualsAndHashCode.Include
    private Integer id;
    
    private String nome;
    
    //Posso colocar a anotação dos atributos (@Column) nos metodos Get, 
    //(Ou todas nos atributos Ou todas nos Gets, para funcionar)
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

    //mesmo com o Lombok posso declarar os metodos explicitamente que inclusive eles terão precedêcia sobre os do Lombok
    @Id
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
    @Column(name = "categoria_pai_id")
	public Integer getCategoriaPaiId() {
		return categoriaPaiId;
	}

	public void setCategoriaPaiId(Integer categoriaPaiId) {
		this.categoriaPaiId = categoriaPaiId;
	}

    
    
}
