package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Sexo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrimeiroCrudTest extends EntityManagerTest {



    @Test
    public void procura(){

        Cliente cliente = new Cliente(1);

        Cliente clienteVerificacao = entityManager.find(Cliente.class, 1);

        Assertions.assertNotNull(clienteVerificacao);

        System.out.println(clienteVerificacao.toString());


    }

    @Test
    public void cria() {
        Cliente cliente = new Cliente(3, "Casimiro Cortez", Sexo.MASCULINO);

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, 3);
        Assertions.assertEquals("Casimiro Cortez", clienteVerificacao.getNome());
    }

    @Test
    public void atualiza() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        entityManager.getTransaction().begin();
        cliente.setNome("Fernando Mascarenhas");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, 1);
        Assertions.assertEquals("Fernando Mascarenhas", clienteVerificacao.getNome());


        Cliente outroCliente = new Cliente(2, "Marcos Risoto", Sexo.MASCULINO);

        entityManager.getTransaction().begin();
        entityManager.merge(outroCliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente outroClienteVerificacao = entityManager.find(Cliente.class, 2);
        Assertions.assertEquals("Marcos Risoto", outroClienteVerificacao.getNome());

    }

    @Test
    public void deleta() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        Assertions.assertNotNull(cliente);

        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, 1);
        Assertions.assertNull(clienteVerificacao);
    }
}
