package com.algaworks.ecommerce.mapeamentoavancado;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Sexo;

public class SecondaryTable extends EntityManagerTest {
	
	@Test
	public void salvarCliente() {
		Cliente cliente = new Cliente();
		cliente.setNome("Carlos Finotti");
		cliente.setSexo(Sexo.MASCULINO);
		cliente.setDataNascimento(LocalDate.of(1990, 1, 1));
		
		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
		Assertions.assertNotNull(clienteVerificacao.getSexo());
	}

}
