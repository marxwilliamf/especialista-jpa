package com.algaworks.ecommerce.relacionamentos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;

public class AutoRelacionamentoTest extends EntityManagerTest {
	
	@Test
	public void verificarRelacionamento() { //exemplo professor
		Categoria categoriaPai = new Categoria();
		categoriaPai.setNome("Eletrônicos");
	
		Categoria categoria = new Categoria();
		categoria.setNome("Celulares");
		categoria.setCategoriaPai(categoriaPai);
		
		entityManager.getTransaction().begin();
		entityManager.persist(categoriaPai);
		entityManager.persist(categoria);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Categoria categoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());
		Assertions.assertNotNull(categoriaVerificacao.getCategoriaPai());
		
	}

	
	@Test
	public void verificarRelacionamentoLista() { //exemplo professor
		Categoria categoriaPai = new Categoria("Eletrônicos", null);
	
		Categoria categoria1 = new Categoria("Celulares", categoriaPai);
		Categoria categoria2 = new Categoria("Computadores", categoriaPai);
		
		entityManager.getTransaction().begin();
		entityManager.persist(categoriaPai);
		entityManager.persist(categoria1);
		entityManager.persist(categoria2);
		entityManager.getTransaction().commit();
		
		entityManager.clear();

		Categoria categoriaPaiVerificacao = entityManager.find(Categoria.class, categoriaPai.getId());
		Assertions.assertNotNull(categoriaPaiVerificacao.getCategorias());

		Categoria categoriaVerificacao = entityManager.find(Categoria.class, categoria1.getId());
		Assertions.assertNotNull(categoriaVerificacao.getCategoriaPai());
		
		System.out.println("------------------------------------------------"); //aqui ele faz uma busca tardia por isso não ficou formatado
		System.out.println("Categoria Pai: " + categoriaPaiVerificacao.getNome() + "\nfilhas...");
		for(Categoria categoria: categoriaPaiVerificacao.getCategorias()) {
			System.out.println(categoria.getNome());
		}
	}
}
