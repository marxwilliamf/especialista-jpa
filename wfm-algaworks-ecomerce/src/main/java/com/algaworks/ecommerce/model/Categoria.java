package com.algaworks.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="categoria")
public class Categoria {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO) //estratégia que deixa o hibernate escolher como vai gerar o id, executando o teste ele criou a tabela hibernate_sequence
//    @EqualsAndHashCode.Include
//    private Integer id;

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
//    @SequenceGenerator(name = "seq", sequenceName = "sequencia_chave_primaria") //essa declaração de sequencia pode ser feita com mesmo nome em outra entidade caso queiram que elas incrementem a sequencia paralelamente
//    @EqualsAndHashCode.Include
//    private Integer id;
    
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq") //mysql não tem sequence mas o hibernate faz uma adaptacão com uma tabela com nome padrão hibernate_sequence
//    @SequenceGenerator(name = "seq", sequenceName = "categoria_sequencia_chave_primaria", allocationSize = 50) //utiliza uma tabela só para categoria com o nome categoria_sequencia_chave_primaria //utiliza a sequence 50 vezes em memória para então voltar na tabela, padrão é 50
//    @EqualsAndHashCode.Include
//    private Integer id;

//    @Id
//    @GeneratedValue(strategy = GenerationType.TABLE) //deixando assim já funciona
//    @EqualsAndHashCode.Include
//    private Integer id;
	
//    @Id
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tabela") //
//    @TableGenerator(name = "tabela", table = "hibernate_sequences", 
//    					pkColumnName = "sequence_name", 
//    					pkColumnValue = "categoria", 
//    					valueColumnName = "next_val", 
//    					initialValue = 0, 
//    					allocationSize = 50)
//    @EqualsAndHashCode.Include
//    private Integer id;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) //usa a função auto incremento do banco de dados
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
