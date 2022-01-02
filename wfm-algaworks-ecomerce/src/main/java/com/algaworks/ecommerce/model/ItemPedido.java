package com.algaworks.ecommerce.model;

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
    private Integer PedidoId;
    private Integer produtoId;
    private BigDecimal precoProduto;
    private Integer quantidade;
    
    

    public ItemPedido() {} //pra funcionar com o JPA precisa ter um cosntrotor vazio

    public ItemPedido(Integer id) {
        this.id = id;
    }

    public ItemPedido(Integer id, String nome, Integer categoriaPaiId) {
        this.id = id;
        this.nome = nome;
        this.categoriaPaiId = categoriaPaiId;
    }

}
