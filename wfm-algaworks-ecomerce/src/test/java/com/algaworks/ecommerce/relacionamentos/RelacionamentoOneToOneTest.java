package com.algaworks.ecommerce.relacionamentos;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.PagamentoCartao;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPagamento;
import com.algaworks.ecommerce.util.Arquivos;

public class RelacionamentoOneToOneTest extends EntityManagerTest {

	@Test
	public void verificaRelacionamento() {
		Pedido pedido = entityManager.find(Pedido.class, 1);

		PagamentoCartao pagamentoCartao = new PagamentoCartao();
		pagamentoCartao.setNumeroCartao("1234");
		pagamentoCartao.setStatus(StatusPagamento.PROCESSANDO);
		pagamentoCartao.setPedido(pedido);

		entityManager.getTransaction().begin();
		entityManager.persist(pagamentoCartao);
		entityManager.getTransaction().commit();

		entityManager.clear();

		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assertions.assertNotNull(pedidoVerificacao.getPagamento());

		PagamentoCartao pagamentoCartaoVerificacao = entityManager.find(PagamentoCartao.class, pagamentoCartao.getId());
		Assertions.assertEquals("1234", pagamentoCartaoVerificacao.getNumeroCartao());

		System.out.println("----------------------------------------------------------");
		System.out.println("Pedido id: " + pedidoVerificacao.getId() + " data: " + pedidoVerificacao.getDataCriacao());
		for (ItemPedido itemPedido : pedidoVerificacao.getItens()) {
			System.out.println("Produto: " + itemPedido.getProduto().getNome() + "\tQuant X Valor: "
					+ itemPedido.getQuantidade() + " x " + itemPedido.getProduto().getPreco());
		}
		System.out.println("Total: " + pedido.getTotal());
		System.out.println("----------------------------------------------------------");
	}

	@Test
	public void verificaRelacionamentoPedidoNotaFiscal() {
		Pedido pedido = entityManager.find(Pedido.class, 1);

		NotaFiscal notaFiscal = new NotaFiscal();
		notaFiscal.setXml(Arquivos.leArquivo("/nota-fiscal.xml"));
		notaFiscal.setDataEmissao(new Date());
		notaFiscal.setPedido(pedido);
		

		entityManager.getTransaction().begin();
		entityManager.persist(notaFiscal);
		entityManager.getTransaction().commit();

		entityManager.clear();

		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assertions.assertNotNull(pedidoVerificacao.getNotaFiscal());

		NotaFiscal notaFiscalVerificacao = entityManager.find(NotaFiscal.class, notaFiscal.getId());
		Assertions.assertNotNull(notaFiscalVerificacao.getPedido().getNotaFiscal().getXml());

	}
}
