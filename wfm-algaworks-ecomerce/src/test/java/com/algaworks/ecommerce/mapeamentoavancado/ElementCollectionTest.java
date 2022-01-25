package com.algaworks.ecommerce.mapeamentoavancado;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Atributo;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Produto;

public class ElementCollectionTest extends EntityManagerTest {
	
	@Test
	public void salvarCliente() {
		
		Cliente cliente = new Cliente();
		cliente.setNome("Fernanda Morais");
		
		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
		Assertions.assertNotNull(clienteVerificacao .getId());
		
	}
	
}
