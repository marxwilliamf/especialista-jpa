package com.algaworks.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.algaworks.ecommerce.listener.GenericoListener;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EntityListeners({GenericoListener.class})
@Entity
@Table(name="produto", uniqueConstraints = @UniqueConstraint(name = "unq_nome", columnNames = "nome"), 
		indexes = @Index(name = "idx_nome", columnList = "nome"))
public class Produto extends EntidadeBaseInteger{
	
	@Column(length = 100, nullable = false, //varchar(100) not null 
			unique = true //semelhante a uniqueConstraints de @Table só que não da pra definir os nomes nem ter mais de uma coluna
			//, table = //usado para sencondary table
			) 
    private String nome;
    
	//@Column(columnDefinition = "varchar(275) default 'descricao'")
	//@Column(columnDefinition = "text") //mysql não pode ter valor por default 
	@Lob // lontext
    private String descricao;
    
	
	//@Column(precision = 19, scale = 2) // ddl// preco decimal(19,2) //padrão
    private BigDecimal preco;
    
    @Column(name = "data_criacao", updatable = false, nullable = false) //padrão pra updatable é true, foi setado pra falso para não se alterar após a inserção
    private LocalDateTime dataCriacao; //datetime(6) not null

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
    @Column(name = "tag", length = 50, nullable = false)
    private List<String> tags;
    
    
    @ElementCollection
    @CollectionTable(name = "produto_atributo", joinColumns = @JoinColumn(name = "produto_id"))
    private List<Atributo> atributos;
    
    
    @PreUpdate
    public void aoAtualizar() {
    	dataUltimaAtualizacao = LocalDateTime.now();
    }
}
