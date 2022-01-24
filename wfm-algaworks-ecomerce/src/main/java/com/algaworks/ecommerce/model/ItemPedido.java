package com.algaworks.ecommerce.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
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

	@EmbeddedId
	private ItemPedidoId id; //os nomes das PK da tabela vem do JPA da classe embutida
	
	@MapsId("pedidoId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id") //com o MapsId eu posso tira insertable = false, updatable = false
    private Pedido pedido;
    
	@MapsId("produtoId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id")
    private Produto produto;
    
    @Column(name = "preco_produto")
    private BigDecimal precoProduto;
    
    private Integer quantidade;

}
