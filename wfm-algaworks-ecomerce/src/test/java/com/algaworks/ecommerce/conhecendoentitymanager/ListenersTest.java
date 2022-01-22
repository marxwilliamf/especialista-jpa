package com.algaworks.ecommerce.conhecendoentitymanager;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.StatusPedido;

public class ListenersTest extends EntityManagerTest{

	
	@Test
	public void carregarEntidades() {
		Pedido pedido = entityManager.find(Pedido.class, 1);
		Produto produto = entityManager.find(Produto.class, 1);
	}
	
	@Test
	public void acionaCallbacks() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
				
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setStatus(StatusPedido.AGUARDANDO);
		
		
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setProduto(entityManager.find(Produto.class, 1));
		itemPedido.setPedido(pedido);
		itemPedido.setPrecoProduto(new BigDecimal(499));
		itemPedido.setQuantidade(1);
		
		pedido.setItens(Arrays.asList(itemPedido)); //precisou setar esse parâmetro para calcular o total em pedido na callback
		
		entityManager.getTransaction().begin();
		
		entityManager.persist(pedido);	
		entityManager.persist(itemPedido);
		
		entityManager.flush(); //assim ele forçaria uma atualização para o status com uma nova data de atualização, mas sem ele deu no mesmo
		
		pedido.setStatus(StatusPedido.PAGO);
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assertions.assertNotNull(pedidoVerificacao.getDataCriacao());
		Assertions.assertNotNull(pedidoVerificacao.getDataUltimaAtualizacao());
				
		
	}
}
