package com.algaworks.ecommerce.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="item_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //usa a função auto incremento do banco de dados
    @EqualsAndHashCode.Include
    private Integer id;
    
//    @Column(name = "pedido_id")
//    private Integer pedidoId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    
//    @Column(name = "produto_id")
//    private Integer produtoId;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id")
    private Produto produto;
    
    
    @Column(name = "preco_produto")
    private BigDecimal precoProduto;
    
    private Integer quantidade;
    
    

    public ItemPedido() {} //pra funcionar com o JPA precisa ter um cosntrutor vazio

    public ItemPedido(Integer id) {
        this.id = id;
    }


}
