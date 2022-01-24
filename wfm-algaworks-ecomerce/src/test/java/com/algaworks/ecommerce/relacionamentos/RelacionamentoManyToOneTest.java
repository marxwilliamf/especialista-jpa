package com.algaworks.ecommerce.relacionamentos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.ItemPedidoId;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.StatusPedido;

public class RelacionamentoManyToOneTest extends EntityManagerTest {
	
	@Test
	public void verificaRelacionamento() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		
		Pedido pedido = new Pedido();
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setDataCriacao(LocalDateTime.now());
		pedido.setTotal(BigDecimal.TEN);
		
		pedido.setCliente(cliente);
		
		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assertions.assertNotNull(pedidoVerificacao.getCliente());
		
		System.out.println(pedidoVerificacao.getCliente().getNome());
	}
	

	@Test
	public void verificaRelacionamentoUtemPedido() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		
		Pedido pedido = new Pedido();
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setDataCriacao(LocalDateTime.now());
		pedido.setTotal(new BigDecimal(5000));
		
		pedido.setCliente(cliente);
		
		Produto produto = new Produto();
		produto.setNome("Computador");
		produto.setDescricao("Alta Performance");
		produto.setPreco(new BigDecimal(5000));
		
		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		entityManager.persist(produto);
		entityManager.flush(); //precisa fazer o flush para que pedido tenha id pra setar em itemPedido //mais pra frente veremos uma anotação que substitui a declaração do flush
		
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setPedidoId(pedido.getId());
		itemPedido.setProdutoId(produto.getId());
		itemPedido.setPrecoProduto(new BigDecimal(5000));
		itemPedido.setQuantidade(1);
		
		entityManager.persist(itemPedido);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		ItemPedido itemPedidoVerificacao = entityManager.find(ItemPedido.class, new ItemPedidoId(pedido.getId(), produto.getId()));
		Assertions.assertNotNull(itemPedidoVerificacao.getPedido());
		
		System.out.println(itemPedidoVerificacao.getPedido().getDataCriacao().toString());
	}
	

	@Test
	public void verificaRelacionamentoUtemPedidoProfessor() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		Produto produto = entityManager.find(Produto.class, 1);
		
		Pedido pedido = new Pedido();
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setDataCriacao(LocalDateTime.now());
		pedido.setTotal(new BigDecimal(499));
		
		pedido.setCliente(cliente);
				
		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		
		entityManager.flush(); //precisa fazer o flush para que pedido tenha id pra setar em itemPedido //mais pra frente veremos uma anotação que substitui a declaração do flush
		
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setPedidoId(pedido.getId());
		itemPedido.setProdutoId(produto.getId());
		itemPedido.setPrecoProduto(new BigDecimal(499));
		itemPedido.setQuantidade(1);
		

		entityManager.persist(itemPedido);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		ItemPedido itemPedidoVerificacao = entityManager.find(ItemPedido.class, new ItemPedidoId(pedido.getId(), produto.getId()));
		Assertions.assertNotNull(itemPedidoVerificacao.getPedido());
		
		System.out.println(itemPedidoVerificacao.getPedido().getDataCriacao().toString());
	}
	
	
}
