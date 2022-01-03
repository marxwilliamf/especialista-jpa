package com.algaworks.ecommerce.model;

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
@Table(name="pagamento_cartao")
public class PagamentoCartao {

    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    
    @Column(name = "pedido_id")
    private Integer pedidoId;
    
    private StatusPagamento status;
    
    private String numero;

}
