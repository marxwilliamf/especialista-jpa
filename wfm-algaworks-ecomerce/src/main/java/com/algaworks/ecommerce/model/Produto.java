package com.algaworks.ecommerce.model;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.algaworks.ecommerce.listener.GenericoListener;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners({GenericoListener.class})
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
    
    @Column(name = "data_criacao", updatable = false) //padrão pra updatable é true, foi setado pra falso para não se alterar após a inserção
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_atualizacao", insertable = false) //insertable false para não ser valorado na inserção
    private LocalDateTime dataUltimaAtualizacao;
    
    @ManyToMany
    @JoinTable(name = "produto_categoria", joinColumns = @JoinColumn(name = "produto_id"), 
    inverseJoinColumns =  @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias;
    
    @OneToOne(mappedBy = "produto") //nem sempre que salvar um produto vai precisar dos registros de estoque, optiona = true vem por padrão
    private Estoque estoque;
    
    @Lob
    private byte[] foto;
    
    @ElementCollection
    @CollectionTable(name = "produto_tag", joinColumns = @JoinColumn(name = "produto_id"))
    @Column(name = "tag")
    private List<String> tags;
    
    
    @ElementCollection
    @CollectionTable(name = "produto_atributo", joinColumns = @JoinColumn(name = "produto_id"))
    private List<Atributo> atributos;
    
    
    @PreUpdate
    public void aoAtualizar() {
    	dataUltimaAtualizacao = LocalDateTime.now();
    }
}
