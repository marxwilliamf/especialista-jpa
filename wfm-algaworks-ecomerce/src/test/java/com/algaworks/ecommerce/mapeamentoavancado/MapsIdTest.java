package com.algaworks.ecommerce.mapeamentoavancado;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.ItemPedidoId;
import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.StatusPedido;
import com.algaworks.ecommerce.util.Arquivos;

public class MapsIdTest extends EntityManagerTest{

	@Test
	public void inserirPagamento() {
		
		Pedido pedido = entityManager.find(Pedido.class, 1);

		NotaFiscal notaFiscal = new NotaFiscal();
		//notaFiscal.setId(pedido.getId()); Agora com MapsId não precisa mais setar explicitamente //setando a PK que também é FK
		notaFiscal.setPedido(pedido);
		notaFiscal.setDataEmissao(new Date());
		notaFiscal.setXml(Arquivos.leArquivo("/nota-fiscal.xml"));		
		

		entityManager.getTransaction().begin();
		entityManager.persist(notaFiscal);
		entityManager.getTransaction().commit();

		entityManager.clear();

		NotaFiscal notaFiscalVerificacao = entityManager.find(NotaFiscal.class, notaFiscal.getId());
		Assertions.assertNotNull(notaFiscalVerificacao);
		Assertions.assertEquals(pedido.getId(), notaFiscalVerificacao.getId());		
	}
	
	@Test
	public void inserirItemPedido() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		Produto produto = entityManager.find(Produto.class, 1);
		
		Pedido pedido = new Pedido();
		
		pedido.setCliente(cliente);
		pedido.setDataCriacao(LocalDateTime.now());
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setTotal(produto.getPreco());
						
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setId(new ItemPedidoId()); //com MapsIs não precisa mais setar assim new ItemPedidoId(1,1)
		itemPedido.setPedido(pedido);
		itemPedido.setProduto(produto);
		itemPedido.setPrecoProduto(new BigDecimal(499));
		itemPedido.setQuantidade(1);
		

		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		entityManager.persist(itemPedido);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		ItemPedido itemPedidoVerificacao = entityManager.find(ItemPedido.class, new ItemPedidoId(pedido.getId(), produto.getId()));
		Assertions.assertNotNull(itemPedidoVerificacao.getPedido());
		
		System.out.println(itemPedidoVerificacao.getPedido().getDataCriacao().toString());
	}
}
