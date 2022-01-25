package com.algaworks.ecommerce.iniciandocomjpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;

public class ConsultandoRegistrosTest extends EntityManagerTest {

    @Test
    public void buscaPorIdentificador() {
        Produto produto = entityManager.find(Produto.class, 1);

        Assertions.assertNotNull(produto);
        Assertions.assertEquals("Kindle", produto.getNome());

    }

    @Test
    public void atualizaReferencia() {
        Produto produto = entityManager.find(Produto.class, 1);

        produto.setNome("Microfone");

        Assertions.assertEquals("Microfone", produto.getNome());
        entityManager.refresh(produto);
        Assertions.assertEquals("Kindle", produto.getNome());
    }
}
