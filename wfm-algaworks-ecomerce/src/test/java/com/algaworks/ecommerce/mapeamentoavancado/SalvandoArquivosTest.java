package com.algaworks.ecommerce.mapeamentoavancado;

import java.io.IOException;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.util.LerNotaFiscal;

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
		
		LerNotaFiscal.salvaArquivo(notaFiscalVerificacao.getXml());
			
		
	}
	
	private static byte[]  carregarNotaFiscal() {
		try {
			return SalvandoArquivosTest.class.getResourceAsStream("/nota-fiscal.xml").readAllBytes();
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
}
