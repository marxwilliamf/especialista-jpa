package com.algaworks.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name="pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //usa a função auto incremento do banco de dados
    @EqualsAndHashCode.Include
    private Integer id;
    
//    @Column(name = "cliente_id")
//    private Integer clienteId;
    
    
//    @ManyToOne 				//caso não seja declarado JoinColumn também funciona e, o nome do campo de junção na tabela será
//    private Cliente cliente; 	//o nome deste atributo no caso "cliente" mais o nome dado na PK na classe cliente em @Column se tiver, no caso id, ficando "cliente_id"  
    
    @ManyToOne
    @JoinColumn(name = "cliente_id") //quem tem o @JoinColumn é o Owner
    private Cliente cliente;
    
    @OneToMany(mappedBy = "pedido")
    List<ItemPedido> itens;
    
    @Column(name = "data_pedido")
    private LocalDateTime dataPedido;
    
    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;
    
    @Column(name = "nota_fiscal_id")
    private Integer notaFiscalId;
    
    private BigDecimal total;
    
    @Enumerated(EnumType.STRING)
    private StatusPedido status;
    
    @Embedded
    private EnderecoEntregaPedido enderecoEntrega;

    public Pedido() {} //pra funcionar com o JPA precisa ter um cosntrutor vazio

    public Pedido(Integer id) {
        this.id = id;
    }


}
