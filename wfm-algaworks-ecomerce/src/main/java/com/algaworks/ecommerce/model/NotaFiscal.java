package com.algaworks.ecommerce.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name="nota_fiscal")
public class NotaFiscal extends EntidadeBaseInteger {

//    //Pk agora é a mesma de FK //@GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    @EqualsAndHashCode.Include
//    @Column(name = "pedido_id")
//    private Integer id;
    
    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    
    @Lob		
    private byte[] xml;
    
    @Column(name = "data_emissao")
    private Date dataEmissao;
    
}
