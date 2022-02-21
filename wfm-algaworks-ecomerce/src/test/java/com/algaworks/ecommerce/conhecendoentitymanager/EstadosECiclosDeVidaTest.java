package com.algaworks.ecommerce.conhecendoentitymanager;

import org.junit.jupiter.api.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;

public class EstadosECiclosDeVidaTest extends EntityManagerTest {

	@Test
	public void analisaEstados() {
		Categoria categoriaNovo = new Categoria(); //estado transiente
		categoriaNovo.setNome("Saúde");
		Categoria categoriaGerenciadaMerge = entityManager.merge(categoriaNovo);
		
		Categoria categoriaGerenciada = entityManager.find(Categoria.class, 1); //estado gerenciado
		
		entityManager.remove(categoriaGerenciada); //estado removido
		entityManager.persist(categoriaGerenciada); //estado gerenciado novamente
		
		entityManager.detach(categoriaGerenciada); // estado detached
	}
}
