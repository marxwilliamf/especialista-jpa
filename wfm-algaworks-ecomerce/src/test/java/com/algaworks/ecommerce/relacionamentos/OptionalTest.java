package com.algaworks.ecommerce.relacionamentos;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.junit.jupiter.api.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;

public class OptionalTest extends EntityManagerTest {

	
//	Optional
//	
//	@ManyToOne(optional = false)
//	Sem optional o padrão é true, que significa opcional, neste caso a consulta fica com left outer join
//	já com optional = false a consulta fica com inner join que é mais performatico que o outer	
	
	@Test
	public void verificaComportamento() {
		Pedido pedido = entityManager.find(Pedido.class, 1);
		//o select de pedido com o relacionamento muitos pra um em cliente na entidade pedid o padão é com optional true, 
		//neste caso o select fica com 
		//left outer join
		//	cliente cliente1_ 
        //		on pedido0_.cliente_id=cliente1_.id 
		//outer join é menos performático do que o inner join
		//caso marcado com optional false o select fica com
		//inner join
        //	cliente cliente1_ 
		//		on pedido0_.cliente_id=cliente1_.id 
	}
}
