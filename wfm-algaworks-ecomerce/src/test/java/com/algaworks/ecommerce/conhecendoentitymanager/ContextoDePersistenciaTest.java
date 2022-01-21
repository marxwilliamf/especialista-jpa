package com.algaworks.ecommerce.conhecendoentitymanager;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;

public class ContextoDePersistenciaTest extends EntityManagerTest {
	
	@Test
	public void usarContextoDePersistencia() {
		
		entityManager.getTransaction().begin();
		
		Produto produto = entityManager.find(Produto.class, 1);
		produto.setPreco(new BigDecimal(100)); //dirtyChecking //setagem de um objeto gerenciado
		
		
		Produto produto2 = new Produto();
		produto2.setNome("Caneca para Café"); //como não é um objeto gerenciado ainda não é dirtyChecking
		produto2.setPreco(new BigDecimal(10));
		produto2.setDescricao("Boa caneca para café");
		entityManager.persist(produto2);

		Produto produto3 = new Produto();
		produto3.setNome("Caneca para Chá"); //como não é um objeto gerenciado ainda não é dirtyChecking
		produto3.setPreco(new BigDecimal(10));
		produto3.setDescricao("Boa caneca para chá");
		produto3 = entityManager.merge(produto3);
				
		entityManager.flush();
		
		produto3.setDescricao(null); //dirtyChecking //setagem de um objeto gerenciado
		
		entityManager.getTransaction().commit();
		
	}
	
}
