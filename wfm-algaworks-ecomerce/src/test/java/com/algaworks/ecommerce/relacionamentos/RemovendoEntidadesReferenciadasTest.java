package com.algaworks.ecommerce.relacionamentos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;

public class RemovendoEntidadesReferenciadasTest extends EntityManagerTest {
	
	@Test
	public void verificaRelacionamento() {
		Pedido pedido = entityManager.find(Pedido.class, 1);
		
		Assertions.assertFalse(pedido.getItens().isEmpty());
		
		entityManager.getTransaction().begin();
		pedido.getItens().forEach(i -> entityManager.remove(i)); //precisa primeiro remover itemPedido //remoção em cascata será visto mais tarde
		entityManager.remove(pedido);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, 1);
		Assertions.assertNull(pedidoVerificacao);		
	}
}