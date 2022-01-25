package com.algaworks.ecommerce.mapeamentoavancado;

import java.io.IOException;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.util.Arquivos;

public class SalvandoArquivosTest extends EntityManagerTest{

	@Test
	public void salvarXmlNota() {
		
		Pedido pedido = entityManager.find(Pedido.class, 1);

		NotaFiscal notaFiscal = new NotaFiscal();
		notaFiscal.setPedido(pedido);
		notaFiscal.setDataEmissao(new Date());
		notaFiscal.setXml(carregarNotaFiscal());		
		

		entityManager.getTransaction().begin();
		entityManager.persist(notaFiscal);
		entityManager.getTransaction().commit();

		entityManager.clear();

		NotaFiscal notaFiscalVerificacao = entityManager.find(NotaFiscal.class, notaFiscal.getId());
		Assertions.assertNotNull(notaFiscalVerificacao.getXml());
		Assertions.assertTrue(notaFiscalVerificacao.getXml().length > 0);
		
		Arquivos.salvaArquivo(notaFiscalVerificacao.getXml(), "/xml.xml");
			
		
	}
	
	private static byte[]  carregarNotaFiscal() {
		try {
			return SalvandoArquivosTest.class.getResourceAsStream("/nota-fiscal.xml").readAllBytes();
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void salvarFoto() {
		
		Produto produto = entityManager.find(Produto.class, 1);

		

		entityManager.getTransaction().begin();
		produto.setFoto(Arquivos.leArquivo("/kindle.jpg"));
		entityManager.getTransaction().commit();

		entityManager.clear();

		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assertions.assertNotNull(produtoVerificacao.getFoto());
		Assertions.assertTrue(produtoVerificacao.getFoto().length > 0);
		
		Arquivos.salvaArquivo(produtoVerificacao.getFoto(), "/kindle.jpg");
			
		
	}
	
}
