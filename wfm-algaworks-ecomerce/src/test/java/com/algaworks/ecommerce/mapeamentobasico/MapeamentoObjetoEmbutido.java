package com.algaworks.ecommerce.mapeamentobasico;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.EnderecoEntregaPedido;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPedido;

public class MapeamentoObjetoEmbutido extends EntityManagerTest{

	
	@Test
	public void analisaMapeamentoObjetoEmbutido() {
		EnderecoEntregaPedido endereco = new EnderecoEntregaPedido();
		endereco.setCep("00000-000");
		endereco.setLogradouro("Rua das Acácias");
		endereco.setNumero("123");
		endereco.setBairro("Centro");
		endereco.setCidade("Guarulhos");
		endereco.setEstado("SP");
		
		Cliente cliente = entityManager.find(Cliente.class, 1);
//		Pedido pedido = new Pedido(1); //Comentado atributo id pois começamos a usar a estratégia IDENTITY
		Pedido pedido = new Pedido(); 
		pedido.setDataPedido(LocalDateTime.now());
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setTotal(new BigDecimal(1000));
		pedido.setEnderecoEntrega(endereco);
		pedido.setCliente(cliente); //como o optional do relacionamento é false precisa setar cliente em pedido
		
		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		entityManager.getTransaction().commit();
		
		entityManager.clear(); //pra buscar do banco de dados
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assertions.assertNotNull(pedidoVerificacao);
		Assertions.assertNotNull(pedidoVerificacao.getEnderecoEntrega());
		Assertions.assertNotNull(pedidoVerificacao.getEnderecoEntrega().getCep());
		
	}
}
