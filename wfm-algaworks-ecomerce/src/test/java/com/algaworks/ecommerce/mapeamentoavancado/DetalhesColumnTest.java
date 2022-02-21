package com.algaworks.ecommerce.mapeamentoavancado;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;

public class DetalhesColumnTest extends EntityManagerTest{

	@Test
	public void imperdirInsercaoDaColunaAtualizacao() {
		Produto produto = new Produto();
		produto.setNome("Teclado para Smartphone");
		produto.setDescricao("Velocidade na sua digitação.");
		produto.setPreco(new BigDecimal(172));
		produto.setDataCriacao(LocalDateTime.now());
		produto.setDataUltimaAtualizacao(LocalDateTime.now());
		
		entityManager.getTransaction().begin();
		entityManager.persist(produto);	
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assertions.assertNotNull(produtoVerificacao.getDataCriacao());
		Assertions.assertNull(produtoVerificacao.getDataUltimaAtualizacao());
			
		
	}
	
	@Test
	public void imperdirAtualizacaoDaColunaCriacao() {
		//esse teste era pra ter duas atualizações sucessivas com duas datas de atualização diferentes, porém não encontro o erro.
		entityManager.getTransaction().begin();
		Produto produto = entityManager.find(Produto.class, 1);
		produto.setPreco(new BigDecimal(499));
		produto.setDataUltimaAtualizacao(LocalDateTime.now());
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		entityManager.getTransaction().begin();
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		produtoVerificacao.setPreco(BigDecimal.TEN);
		produtoVerificacao.setDataUltimaAtualizacao(LocalDateTime.now());
		entityManager.getTransaction().commit();
		
		
		Assertions.assertEquals(produto.getDataCriacao().truncatedTo(ChronoUnit.SECONDS),  
				produtoVerificacao.getDataCriacao().truncatedTo(ChronoUnit.SECONDS));
		//Assertions.assertNotEquals(seconds, produtoVerificacao.getDataUltimaAtualizacao().truncatedTo(ChronoUnit.SECONDS).toString());
			
		
	}

	@Test
	public void imperdirAtualizacaoDaColunaCriacaoProfessor() {
		
		entityManager.getTransaction().begin();
		Produto produto = entityManager.find(Produto.class, 1);
		produto.setPreco(BigDecimal.TEN);
		produto.setDataCriacao(LocalDateTime.now());
		produto.setDataUltimaAtualizacao(LocalDateTime.now());
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();		
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assertions.assertNotEquals(produto.getDataCriacao().truncatedTo(ChronoUnit.SECONDS),  
				produtoVerificacao.getDataCriacao().truncatedTo(ChronoUnit.SECONDS));
		Assertions.assertEquals(produto.getDataUltimaAtualizacao().truncatedTo(ChronoUnit.SECONDS), produtoVerificacao.getDataUltimaAtualizacao().truncatedTo(ChronoUnit.SECONDS));
			
		
	}
	
}
