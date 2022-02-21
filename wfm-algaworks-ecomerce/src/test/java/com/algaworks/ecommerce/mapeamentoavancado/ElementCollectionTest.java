package com.algaworks.ecommerce.mapeamentoavancado;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Sexo;

public class ElementCollectionTest extends EntityManagerTest {

	@Test
	public void salvarCliente() {
		
		Cliente cliente = new Cliente();
		cliente.setNome("Fernanda Morais");
		cliente.setCpf("000.000.000-06");
		cliente.setSexo(Sexo.FEMININO);;
		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
		Assertions.assertNotNull(clienteVerificacao .getId());
		
	}
	
}
