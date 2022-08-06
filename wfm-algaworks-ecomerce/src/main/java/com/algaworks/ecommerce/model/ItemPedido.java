package com.algaworks.ecommerce.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
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
	
	@MapsId("pedidoId") //essa coluna será uma pk
    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id", nullable = false, //não precisaria nullable = false por causa do MapsId, mas pode-se deixar explicito caso queira 
    foreignKey = @ForeignKey(name = "fk_item_pedido_pedido")) //com o MapsId eu posso tira insertable = false, updatable = false
    private Pedido pedido;
    
	@MapsId("produtoId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id", nullable = false,
    foreignKey = @ForeignKey(name = "fk_item_pedido_produto"))
    private Produto produto;
    
    @Column(name = "preco_produto", nullable = false) //decimal(19, 2) not null //precisão e escala já são esses valores por padrão não precisa informar 
    private BigDecimal precoProduto;
    
    @Column(nullable = false)
    private Integer quantidade; //integer not null //integet int(11) padrão mysql 

}
