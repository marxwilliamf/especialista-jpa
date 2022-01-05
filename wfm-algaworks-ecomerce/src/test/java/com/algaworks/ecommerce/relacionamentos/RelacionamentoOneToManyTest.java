package com.algaworks.ecommerce.relacionamentos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.StatusPedido;

public class RelacionamentoOneToManyTest extends EntityManagerTest {
	
	@Test
	public void verificaRelacionamento() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		
		Pedido pedido = new Pedido();
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setDataPedido(LocalDateTime.now());
		pedido.setTotal(BigDecimal.TEN);
		
		pedido.setCliente(cliente);
		
		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
		Assertions.assertFalse(clienteVerificacao.getPedidos().isEmpty());
		
		System.out.println(clienteVerificacao.getPedidos().get(0).getDataPedido().toString());
	}
	
	
	@Test
	public void verificaRelacionamentoPedido() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		Produto produto1 = entityManager.find(Produto.class, 1);
		Produto produto2 = entityManager.find(Produto.class, 3);
		
		Pedido pedido = new Pedido();
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setDataPedido(LocalDateTime.now());
		pedido.setTotal(new BigDecimal(1998));
		
		pedido.setCliente(cliente);
				
		ItemPedido itemPedido1 = new ItemPedido();
		itemPedido1.setPedido(pedido);
		itemPedido1.setProduto(produto1);
		itemPedido1.setPrecoProduto(new BigDecimal(499));
		itemPedido1.setQuantidade(1);

		ItemPedido itemPedido2 = new ItemPedido();
		itemPedido2.setPedido(pedido);
		itemPedido2.setProduto(produto2);
		itemPedido2.setPrecoProduto(new BigDecimal(1499));
		itemPedido2.setQuantidade(1);

		entityManager.getTransaction().begin();
		entityManager.persist(itemPedido1);
		entityManager.persist(itemPedido2);
		entityManager.persist(pedido); //Se for na mesma transação posso colocar pedido depois que o hibernate corrige
		entityManager.getTransaction().commit();
		
		//--------------------------------------
		
//		entityManager.getTransaction().begin();
//		entityManager.persist(pedido);					//Caso for em transação diferente preciso persistir primeiro pedido, caso contrário da erro
//		entityManager.getTransaction().commit();
//		
//		entityManager.getTransaction().begin();
//		entityManager.persist(itemPedido1);
//		entityManager.persist(itemPedido2);
//		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assertions.assertFalse(pedidoVerificacao.getItens().isEmpty());
		
		System.out.println("---------------------------------------------\nCompra:");
		for(ItemPedido itemPedido:pedidoVerificacao.getItens()) {
			System.out.println("Produto " + itemPedido.getProduto().getNome() + " valor: " + itemPedido.getPrecoProduto().toString());			
		}
		System.out.println("\t\t\tTotal: " + pedido.getTotal());

	}
	
	@Test
	public void verificaRelacionamentoUtemPedidoProfessor() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		Produto produto = entityManager.find(Produto.class, 1);
		
		Pedido pedido = new Pedido();
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setDataPedido(LocalDateTime.now());
		pedido.setTotal(BigDecimal.TEN);
		
		pedido.setCliente(cliente);
				
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setPrecoProduto(produto.getPreco());
		itemPedido.setQuantidade(1);
		itemPedido.setPedido(pedido);
		itemPedido.setProduto(produto);
		
		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		entityManager.persist(itemPedido);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assertions.assertFalse(pedidoVerificacao.getItens().isEmpty());
		
		System.out.println(pedidoVerificacao.getItens().get(0).getProduto().getNome());
	}
}
