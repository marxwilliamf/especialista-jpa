package com.algaworks.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="nota_fiscal")
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //usa a função auto incremento do banco de dados
    @EqualsAndHashCode.Include
    private Integer id;
    
    @OneToOne
//    @JoinColumn(name = "pedido_id")
    @JoinTable(name = "pedido_nota_fiscal", joinColumns = @JoinColumn(name = "nota_fiscal_id", unique = true), //resultado final igual JoinColumn porém com a criação de uma tabela "pedido_nota_fiscal" com as relações 
    		inverseJoinColumns = @JoinColumn(name = "pedido_id", unique = true)) //teste é o mesmo que testou com @JoinColumn
    private Pedido pedido; //note que JoinTable não precisa necessariamente ser para Listas
    
    private String xml;
    
    @Column(name = "data_emissao")
    private Date dataEmissao;
    
}
