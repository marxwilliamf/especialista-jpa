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
@Table(name="estoque")
public class Estoque {

    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    
    @Column(name = "produto_id")
    private Integer produtoId;
    
    private Integer quantidade;
    

    public Estoque() {} //pra funcionar com o JPA precisa ter um cosntrotor vazio

    public Estoque(Integer id) {
        this.id = id;
    }

}
