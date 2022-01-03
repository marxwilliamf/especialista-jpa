package com.algaworks.ecommerce.model;

import java.math.BigDecimal;

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
@Table(name="item_pedido")
public class ItemPedido {

    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    
    @Column(name = "pedido_id")
    private Integer PedidoId;
    
    @Column(name = "produto_id")
    private Integer produtoId;
    
    @Column(name = "preco_produto")
    private BigDecimal precoProduto;
    
    private Integer quantidade;
    
    

    public ItemPedido() {} //pra funcionar com o JPA precisa ter um cosntrutor vazio

    public ItemPedido(Integer id) {
        this.id = id;
    }


}
