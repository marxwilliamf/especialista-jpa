package com.algaworks.ecommerce.mapeamentoavancado;

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

public class ChaveCompostaTest extends EntityManagerTest {

	@Test
	public void salvarItem() {
		entityManager.getTransaction().begin();
		
		Cliente cliente = entityManager.find(Cliente.class, 1);
		Produto produto = entityManager.find(Produto.class, 1);
		
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setDataCriacao(LocalDateTime.now());
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setTotal(produto.getPreco());
		
		entityManager.persist(pedido);
		
		entityManager.flush(); //precisa fazer o flush para que pedido tenha id pra setar em itemPedido //mais pra frente veremos uma anotação que substitui a declaração do flush		
		ItemPedido itemPedido = new ItemPedido();
//		itemPedido.setPedidoId(pedido.getId()); //IdClass //como foi feito o flush está setado
//		itemPedido.setProdutoId(produto.getId());
		itemPedido.setId(new ItemPedidoId(pedido.getId(), produto.getId()));
		itemPedido.setPedido(pedido);
		itemPedido.setProduto(produto);
		itemPedido.setPrecoProduto(produto.getPreco());
		itemPedido.setQuantidade(1);
		
		entityManager.persist(itemPedido);
		
		entityManager.getTransaction().commit();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, 1);
		Assertions.assertNotNull(pedidoVerificacao);
		Assertions.assertFalse(pedidoVerificacao.getItens().isEmpty());
		
	}
	
	@Test
	public void buscarItem() {
		ItemPedido itemPedido = entityManager.find(ItemPedido.class, new ItemPedidoId(1,1));
			
		Assertions.assertNotNull(itemPedido);	
	}
	
}
