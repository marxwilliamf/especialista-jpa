package com.algaworks.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="pagamento_cartao")
public class PagamentoCartao extends EntidadeBaseInteger {

//    @Id
//    @EqualsAndHashCode.Include
//    @Column(name = "pedido_id")
//    private Integer id;
    
	@MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    
    @Enumerated(EnumType.STRING)
    private StatusPagamento status;
    
    private String numero;

}
