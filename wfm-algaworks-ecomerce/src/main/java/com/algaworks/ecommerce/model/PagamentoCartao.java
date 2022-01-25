package com.algaworks.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
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
@Table(name="pagamento_cartao")
public class PagamentoCartao {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "pedido_id")
    private Integer id;
    
	@MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    
    @Enumerated(EnumType.STRING)
    private StatusPagamento status;
    
    private String numero;

}
