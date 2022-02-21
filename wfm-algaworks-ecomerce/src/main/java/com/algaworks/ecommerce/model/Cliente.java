package com.algaworks.ecommerce.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SecondaryTable(name = "cliente_detalhe", pkJoinColumns = @PrimaryKeyJoinColumn(name = "cliente_id"))
@Entity
@Table(name="cliente", // schema = "wfm-ecommerce" //schema e catalog não são levados em conta na hora de gerar o banco automáticamente
	//, catalog = //catalogos diferentes G	EX: ORACLE só no persistence.xml não basta é necessario declarar na classe
	uniqueConstraints = { @UniqueConstraint(name = "unq_cpf", columnNames = {"cpf"})}, // pode ser um "array" {} de Constraints, caso seja um só não precisa chaves, mas como exemplo pra ficar explícito
	indexes = { @Index(name = "idx_nome", columnList = "nome") } ) //columnList = "nome, cpf") //coluna nome é escrita igual a como é tabela e ou em @column
	public class Cliente extends EntidadeBaseInteger {
	
	@Column(length = 100, nullable = false) //varchar(100) not null
    private String nome;
    
	@Column(length = 14, nullable = false) //varchar(100) not null
    private String cpf;
    
    @ElementCollection
    @CollectionTable(name = "cliente_contato", joinColumns = @JoinColumn(name = "cliente_id"))
    @MapKeyColumn(name = "tipo")
    @Column(name = "descricao")
    private Map<String, String> contatos;
    
    @Transient //não cria campo primeiro_nome na tabela ele é ignorado tanto pta salvar, buscar e atualizar, pois é um campo transiente, neste caso o prenchemos no callback @PostLoad
    private String primeiroNome;
    
    @Column(table = "cliente_detalhe", length = 30, nullable = false) //varchar(30) not null
    @Enumerated(EnumType.STRING) // para guardar o nome da enumeração, caso contrario, por padrão é @Enumerated(EnumType.ORDINAL) 
    private Sexo sexo;
    
    @Column(name = "data_nascimento", table = "cliente_detalhe")
    private LocalDate dataNascimento;
    
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    public Cliente() {} //pra funcionar com o JPA precisa ter um cosntrutor vazio

    public Cliente(Integer id) {
        this.id = id;
    }

    public Cliente(Integer id, String nome, String cpf, Sexo sexo) {
    	this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.sexo = sexo;
    }
    
    public Cliente(String nome, String cpf, Sexo sexo) {
        this.nome = nome;
        this.cpf = cpf;
        this.sexo = sexo;
    }
    
    public Cliente(Integer id, String nome, Sexo sexo) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
    }
    
    @PostLoad
    public void configurarPrimeiroNome() {
    	if(nome != null && !nome.isBlank()) {
    		int index = nome.indexOf(" ");
    		if(index > -1) {
    			primeiroNome = nome.substring(0, index);
    		}
    	}
    }

}
