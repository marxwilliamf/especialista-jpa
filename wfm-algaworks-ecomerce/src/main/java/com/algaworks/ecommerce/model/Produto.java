package com.algaworks.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //usa a função auto incremento do banco de dados
    @EqualsAndHashCode.Include
    private Integer id;
    
    private String nome;
    
    private String descricao;
    
    private BigDecimal preco;
    
    @ManyToMany
    @JoinTable(name = "produto_categoria", joinColumns = @JoinColumn(name = "produto_id"), 
    inverseJoinColumns =  @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias;
    
    @OneToOne(mappedBy = "produto") //nem sempre que salvar um produto vai precisar dos registros de estoque, optiona = true vem por padrão
    private Estoque estoque;
    
}
