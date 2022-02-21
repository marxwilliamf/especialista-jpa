package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Sexo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrimeiroCrudTest extends EntityManagerTest {

    @Test
    public void procura(){
        
        Cliente cliente = entityManager.find(Cliente.class, 1);

        Assertions.assertNotNull(cliente);

        System.out.println(cliente.getNome());


    }

    @Test
    public void cria() {
//        Cliente cliente = new Cliente(3, "Casimiro Cortez", Sexo.MASCULINO); //Comentado atributo id pois começamos a usar a estratégia IDENTITY
        Cliente cliente = new Cliente("Casimiro Cortez", "000.000.000-05", Sexo.MASCULINO);

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
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


        Cliente outroCliente = new Cliente(3, "Felipe Mariano", "000.000.000-03", Sexo.MASCULINO); //necessario o id para identificar o cliente a ser atualizado

        entityManager.getTransaction().begin();
        entityManager.merge(outroCliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente outroClienteVerificacao = entityManager.find(Cliente.class, outroCliente.getId());
        Assertions.assertEquals("Felipe Mariano", outroClienteVerificacao.getNome());

    }

    @Test
    public void deleta() {
        Cliente cliente = entityManager.find(Cliente.class, 2); //cliente de id 2 por que não tem nenhum pedido para esse cliente

        Assertions.assertNotNull(cliente);

        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assertions.assertNull(clienteVerificacao);
    }
}
