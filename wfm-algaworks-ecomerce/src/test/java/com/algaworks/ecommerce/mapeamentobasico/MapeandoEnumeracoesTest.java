package com.algaworks.ecommerce.mapeamentobasico;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Sexo;

public class MapeandoEnumeracoesTest extends EntityManagerTest {
	
	@Test
	public void testarEnum() {
		Cliente cliente = new Cliente(6, "Jose Mineiro", Sexo.MASCULINO);
		
		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();
		
		entityManager.clear(); //pra buscar do banco de dados
		
		Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
		Assertions.assertNotNull(clienteVerificacao);
	}
	
}
