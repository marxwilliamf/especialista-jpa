package com.algaworks.ecommerce.mapeamentoavancado;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.ItemPedidoId;
import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.StatusPedido;

public class MapsIdTest extends EntityManagerTest{

	@Test
	public void inserirPagamento() {
		
		Pedido pedido = entityManager.find(Pedido.class, 1);

		NotaFiscal notaFiscal = new NotaFiscal();
		notaFiscal.setId(pedido.getId()); //setando a PK que também é FK
		notaFiscal.setPedido(pedido);
		notaFiscal.setDataEmissao(new Date());
		notaFiscal.setXml("<xml/>");		
		

		entityManager.getTransaction().begin();
		entityManager.persist(notaFiscal);
		entityManager.getTransaction().commit();

		entityManager.clear();

		NotaFiscal notaFiscalVerificacao = entityManager.find(NotaFiscal.class, notaFiscal.getId());
		Assertions.assertNotNull(notaFiscalVerificacao);
		Assertions.assertEquals(pedido.getId(), notaFiscalVerificacao.getId());		
	}
	
}
