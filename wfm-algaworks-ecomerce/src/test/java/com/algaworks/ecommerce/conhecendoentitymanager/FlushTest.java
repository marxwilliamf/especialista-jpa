package com.algaworks.ecommerce.conhecendoentitymanager;

import org.jboss.jandex.ThrowsTypeTarget;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPedido;

public class FlushTest extends EntityManagerTest{
	
	@Test
	public void trataExcecao() {
		RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () ->
			{chamarFlush();
				}, "RuntimeException foi esperado");
		
		Assertions.assertEquals("Pedido ainda não foi pago.", thrown.getMessage());
	}
	
	//@Test(expected = Exception.class) //no curso com junit4, havia somente este metodo com essa linha descomentada, neste caso foi usado mais um metodo antes trataExcecao
	public void chamarFlush() {
		try { 
			entityManager.getTransaction().begin();
			
			Pedido pedido = entityManager.find(Pedido.class, 1);
			pedido.setStatus(StatusPedido.PAGO);
			
			entityManager.flush(); //comando flush força tudo que esta na memória ser persistido no baco // evitar usar o flush, já evita usar rollback 			
			//entityManager.clear(); //o flush poderia ser usado em conjunto com o clear para mandar tudo pro banco e após o clear buscar direto do banco			
		
			if(pedido.getPagamento() == null) {
				throw new RuntimeException("Pedido ainda não foi pago.");
			}
			
			entityManager.getTransaction().commit();
		} catch(Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		}
	}
	
	@Test
	public void trataExcecao2() {
		RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () ->
			{chamarFlush2();
				}, "RuntimeException foi esperado");
		
		Assertions.assertEquals("Pedido ainda não foi pago.", thrown.getMessage());
	}
	
	//@Test(expected = Exception.class) //no curso com junit4, havia somente este metodo com essa linha descomentada, neste caso foi usado mais um metodo antes trataExcecao
	public void chamarFlush2() {
		try { 
			entityManager.getTransaction().begin();
			
			Pedido pedido = entityManager.find(Pedido.class, 1);
			pedido.setStatus(StatusPedido.PAGO);						
			
			//entityManager.flush();//esse teste chamarFlush2 era pra mostrar que o Hibernate fazia u flush antes de um createQuery, que ele ia fazer um update no banco para pago antes da pesquisa da JPQL, porém em versões mais novas do Hibernate isso não ocorre, caso queira simula o que ele fazia é necessário descomentar essa linha
			
			//Pedido pedidoPago = entityManager.find(Pedido.class, 1); //caso você queira pegar de novo com status pago ele vai pegar direto da memória como pago
			
			//uma consulta obrigava o JPA sincronizar o que ele tinha na memória
			Pedido pedidoPago = entityManager.createQuery("select p from pedido p where p.id = 1", Pedido.class).getSingleResult(); //já neste caso ele busca direto do banco, não no cache cache de primeiro nível, por isso esse método força um flush para pegar o status atualizado, (bem era pra ser isso que ele fazia, mas como comentado logo ali em cima no comando flush, nas versões mais novas quem sabe apartir de 5.2 ele não faz mais isso)
			
			Assertions.assertEquals(pedido.getStatus(), pedidoPago.getStatus());
			
			entityManager.getTransaction().commit();
		} catch(Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		}
	}
	
}