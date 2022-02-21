package com.algaworks.ecommerce.mapeamentoavancado;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pagamento;
import com.algaworks.ecommerce.model.PagamentoCartao;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Sexo;
import com.algaworks.ecommerce.model.StatusPagamento;

public class HerancaTest extends EntityManagerTest {

	@Test
	public void salvarCliente() {
		Cliente cliente = new Cliente();
		cliente.setNome("Fernando Morais");
		cliente.setSexo(Sexo.FEMININO);
		cliente.setCpf("000.000.000-07");
		
		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
		Assertions.assertNotNull(clienteVerificacao.getId());
	}
	
	@Test
	public void buscarPagamento() {
		List<Pagamento> pagamentos = entityManager.createQuery("select p from Pagamento p").getResultList();
		Assertions.assertFalse(pagamentos.isEmpty());
	}
	
	@Test
	public void IncluirPagamentoPedido() {
		Pedido pedido = entityManager.find(Pedido.class, 1);
		
		PagamentoCartao pagamentoCartao = new PagamentoCartao();
		pagamentoCartao.setPedido(pedido);
		pagamentoCartao.setStatus(StatusPagamento.PROCESSANDO);
		pagamentoCartao.setNumeroCartao(null);
		
		entityManager.getTransaction().begin();
		entityManager.persist(pagamentoCartao);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, 1);
		Assertions.assertNotNull(pedidoVerificacao.getPagamento());

		
	}	

}
