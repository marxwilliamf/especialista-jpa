package com.algaworks.ecommerce.conhecendoentitymanager;

import org.junit.jupiter.api.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;

public class CacheDePrimeiroNivelTest extends EntityManagerTest {
	
	@Test
	public void verificaCache() { 
		Produto produto = entityManager.find(Produto.class, 1);
		System.out.println(produto.getNome());
		
		System.out.println("------------------------------------"); //aqui mostra quando eu chamo a mesma instancia de produto ele não busca novamente no banco, devido a cache de primeiro nível
		
//		entityManager.clear(); //caso eu querira verificar como ele faria sem utilizar a cache é só descomentar esse código
		
//		entityManager.close(); //ou esse trecho de código
//		entityManager = entityManagerFactory.createEntityManager();
		
		Produto produtoResgatado = entityManager.find(Produto.class, produto.getId());
		System.out.println(produtoResgatado.getNome());
		
	}
	
//resultado do teste
//	Kindle
//	------------------------------------
//	Kindle
//sem pesquisa no banco, lembrando que precisa estar mostrando as pesquisas SQL
//persistence.xml
//	<property name="hibernate.show_sql" value="true" />
//	<property name="hibernate.format_sql" value="true" />

}
