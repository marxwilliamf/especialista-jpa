package com.algaworks.ecommerce.relacionamentos;

import org.junit.jupiter.api.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;

public class EagerELazyTest extends EntityManagerTest {

	
	//EAGER E LAZY
	//Eager busca tudo no mesmo acesso ao banco
	//Lazy deixa pra acessar informações secundárias só quando necessário
	//padrão para entidades gregadas é Eager
	//padrão para listas e Lazy
	//pode se mudar o padrãono mapeamento da relação
	//ex.: @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
	
	
	@Test
	public void verificaComportamento() {
		Pedido pedido = entityManager.find(Pedido.class, 1);
		
		pedido.getItens(); //aqui no lazy nem chega a buscar no banco
		pedido.getItens().isEmpty(); //precisa ter que usar para fazer a chamada, já com Eager já terá buscado tudo de uma vez
	}
}
