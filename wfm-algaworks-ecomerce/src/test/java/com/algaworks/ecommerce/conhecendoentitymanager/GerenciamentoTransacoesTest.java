package com.algaworks.ecommerce.conhecendoentitymanager;

import org.jboss.jandex.ThrowsTypeTarget;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPedido;

public class GerenciamentoTransacoesTest extends EntityManagerTest{

	
	@Test
	public void abrirFecharCancelarTransacao() {
		Pedido pedido = entityManager.find(Pedido.class, 1);
		
		pedido.setStatus(StatusPedido.PAGO);
		
		entityManager.getTransaction().begin();
		
		if(pedido.getPagamento() != null) {
			entityManager.getTransaction().commit();
		} else {
			entityManager.getTransaction().rollback(); //essa utilização de rollback é um caso hipotético, normalmente se valida antes os dados.
		}
		
	}
	
	@Test
	public void abrirFecharCancelarTransacaoSemUsarRollback() { //essa é uma forma melhor do que usar rollback
		Pedido pedido = entityManager.find(Pedido.class, 1);
		
		entityManager.getTransaction().begin();
		if(pedido.getPagamento() != null) {
			pedido.setStatus(StatusPedido.PAGO);
		} 
		entityManager.getTransaction().commit();
	}
	
	
	@Test
	public void abrirFecharCancelarTransacaoNegocio() {
		RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () ->
			{abrirFecharCancelarTransacaoMaisPertoDoReal();
				}, "RuntimeException foi esperado");
		
		Assertions.assertEquals("Pedido ainda não foi pago.", thrown.getMessage());
	}
	
	//@Test//(expected = Exception.class) junit4, neste caso foi usado mais um metodo antes abrirFecharCancelarTransacaoNegocio
	public void abrirFecharCancelarTransacaoMaisPertoDoReal() {
		try { //simula um interceptador que será visto mais pra frente
			entityManager.getTransaction().begin();
			metodoDeNegocio();
			entityManager.getTransaction().commit();
		} catch(Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		}
	}
	
	public void metodoDeNegocio() {
		Pedido pedido = entityManager.find(Pedido.class, 1);
		pedido.setStatus(StatusPedido.PAGO);
		
		if(pedido.getPagamento() == null) {
			throw(new RuntimeException("Pedido ainda não foi pago."));
		}
	}	
}