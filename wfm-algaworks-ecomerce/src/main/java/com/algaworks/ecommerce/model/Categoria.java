package com.algaworks.ecommerce.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="categoria")
public class Categoria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) //usa a função auto incremento do banco de dados
  @EqualsAndHashCode.Include
  private Integer id;
    
    private String nome;    
    
//    @Column(name = "categoria_pai_id")
//    private Integer categoriaPaiId;
    
    
    @ManyToOne
    @JoinColumn(name = "categoria_pai_id")
    private Categoria categoriaPai;
    
    @OneToMany(mappedBy = "categoriaPai")
    List<Categoria> categorias;

    public Categoria() {
    	
    }
    
    public Categoria(String nome, Categoria categoriaPai) {
        this.nome = nome;
        this.categoriaPai = categoriaPai;
    }
}
