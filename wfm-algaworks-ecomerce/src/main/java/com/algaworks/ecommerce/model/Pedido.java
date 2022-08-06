package com.algaworks.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.algaworks.ecommerce.listener.GenericoListener;
import com.algaworks.ecommerce.listener.GerarNotaFiscalListener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EntityListeners({GerarNotaFiscalListener.class, GenericoListener.class})
@Entity
@Table(name="pedido")
public class Pedido extends EntidadeBaseInteger{
    
    @ManyToOne(optional = false) //com o optional = false na hora de fazer o join ele usa join column e não left join column     //com optional = false, é bom o nullable estar como false também, pricipalmente se o banco for gerado por código 
    @JoinColumn(name = "cliente_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pedido_cliente")) //quem tem o @JoinColumn é o Owner //
    private Cliente cliente;
    
    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
    List<ItemPedido> itens;

    @Column(name = "data_criacao", updatable = false, nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_atualizacao", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;
    
//    @OneToOne(mappedBy = "pedido") //a volta fica igual mesmo com @JoinTable no Owner para @OneToOne
    @OneToOne(mappedBy = "pedido") //nem sempre que eu tenho um pedido eu tenho a nota fiscal então optional fica true que é o padrão quando não é declarado
    private NotaFiscal notaFiscal;
    
    @Column(//precision = 19, scale = 2, //padrão 
    		nullable = false)
    private BigDecimal total;
    
    @Column(length = 30, nullable = false) //length somente para String
    @Enumerated(EnumType.STRING)
    private StatusPedido status;
    
    @Embedded
    private EnderecoEntregaPedido enderecoEntrega;

    @OneToOne(mappedBy = "pedido")
    private Pagamento pagamento;
    
    public Pedido() {} //pra funcionar com o JPA precisa ter um cosntrutor vazio

    public Pedido(Integer id) {
        this.id = id;
    }
    
    public boolean isPago() {
    	return StatusPedido.PAGO.equals(status);
    }

//  @PrePersist //pode ter duas anotações no mesmo metodo, porem não pode repetir uma anotação de callback
//  @PreUpdate
  public void calcularTotal() {
  	if(itens != null) {
  		total = itens.stream().map(ItemPedido::getPrecoProduto)
  				.reduce(BigDecimal.ZERO, BigDecimal::add);
  	}
  }

  
    @PrePersist
    public void aoPersistir() {
    	dataCriacao = LocalDateTime.now();
    	calcularTotal();
    }
    
    @PreUpdate
    public void aoAtualizar() {
    	dataUltimaAtualizacao = LocalDateTime.now();
    	calcularTotal();
    }    
    @PostPersist
    public void aposPersistir() {
    	System.out.println("Após persistir Pedido.");
    }
    
    @PostUpdate
    public void aposAtualizar() {
    	System.out.println("Após atualizar Pedido.");
    }
    
    @PreRemove
    public void antesDeRemover() {
    	System.out.println("Antes de remover Pedido.");
    }
    
    @PostRemove
    public void aposRemover() {
    	System.out.println("Após remover Pedido.");
    }

    @PostLoad
    public void aposCarregar() {
    	System.out.println("Após carregar o Pedido.");
    }

}
