package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class OperacoesComTransacoesTest extends EntityManagerTest {

    @Test
    public void impedirOperacaoComBancoDeDados() {
        Produto produto = entityManager.find(Produto.class, 1);

        entityManager.detach(produto); // desvincula o objeto produto do Entity Manager, operações com ele não surtirão efeito

        entityManager.getTransaction().begin();
        produto.setNome("Kindle Paperwhite 2ª Geração");
        entityManager.getTransaction().commit();

        entityManager.clear(); // precisa limpar a memória para fazer a asserção por que o objeto ainda estará na memória

        Produto produtoVerificação = entityManager.find(Produto.class, 1);
        Assertions.assertEquals("Kindle", produtoVerificação.getNome()); //verifica que não alterou o nome do objeto
    }


    @Test
    public void inserirObjetoPersistMerge(){
        Produto produtoPersist = new Produto();

        produtoPersist.setId(5);
        produtoPersist.setNome("Smartphone One Plus");
        produtoPersist.setDescricao("O melhor processador");
        produtoPersist.setPreco(new BigDecimal(2000));

        entityManager.getTransaction().begin();
        entityManager.persist(produtoPersist); //só serve para persistir, não serve para atualizar, garate uma chamada de insert de um objeto novo
        produtoPersist.setDescricao("O melhor processador da sua geração"); //como ele ja esta sendo gerenciado pelo EntityManager ele não faz select fazendo direto um update.

//        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear(); // se eu não faço esse clear ele busca na memória e não no banco de dados // com o clear ele faz mais um select

        Produto produtoVerificação = entityManager.find(Produto.class, produtoPersist.getId());
        Assertions.assertNotNull(produtoVerificação);
        System.out.println(produtoVerificação.toString());

        Produto produtoMerge = new Produto();
        produtoMerge.setId(6);
        produtoMerge.setNome("Notebook Dell");
        produtoMerge.setDescricao("O melhor da Categoria");
        produtoMerge.setPreco(new BigDecimal(7000));

//        entityManager.getTransaction().begin();
//        entityManager.merge(produtoMerge); //merge faz uma cópia de produto para gerenciar com EntityManager
//        produtoMerge.setNome("Notebook Dell Pro"); // nesse caso ele não vai atualizar pois não é um objeto gerenciado
//        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        produtoMerge = entityManager.merge(produtoMerge); //merge faz uma cópia de produto para gerenciar com EntityManager
        produtoMerge.setNome("Notebook Dell Pro"); // nesse caso ele vai atualizar pois o método retorna o objeto gerenciado
        entityManager.getTransaction().commit();
    }


    @Test
    public void inserirObjetoComMerge(){
        Produto produto = new Produto();

        produto.setId(4);
        produto.setNome("Microfone Rode Videmic");
        produto.setDescricao("A melhor qualidade de som.");
        produto.setPreco(new BigDecimal(1000));

        //entityManager.merge(produto); //o merge também pode ficar fora da transação aguardando por uma.

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear(); // se eu não faço esse clear ele busca na memória e não no banco de dados // com o clear ele faz mais um select

        Produto produtoVerificação = entityManager.find(Produto.class, produto.getId());
        Assertions.assertNotNull(produtoVerificação);
        System.out.println(produtoVerificação.toString());
    }


    @Test
    public void atualizaObjetoGerenciado() {
        Produto produto = entityManager.find(Produto.class, 1);

        //produto.setNome("Kindle Paperwhite  2ª Geração"); //pode inclusive ficar fora da transação, esperando por uma.
        //não precisa setar descrição e preço pois foi buscado objeto produto na meméria

        entityManager.getTransaction().begin();
        produto.setNome("Kindle Paperwhite 2ª Geração");
        entityManager.getTransaction().commit();

        entityManager.clear(); // precisa limpar a memória para fazer a asserção por que o objeto ainda estará na memória

        Produto produtoVerificação = entityManager.find(Produto.class, 1);
        Assertions.assertEquals("Kindle Paperwhite 2ª Geração", produtoVerificação.getNome());
    }

    @Test
    public void atualizaObjeto() {
        Produto produto = new Produto();

        produto.setId(1);
        produto.setNome("Kindle Paperwhite");
        produto.setDescricao("Conheça o novo Kindle"); //caso não descreva uma caracteristica ele substitui por vazio.
        produto.setPreco(new BigDecimal(599));

        entityManager.getTransaction().begin();
        entityManager.merge(produto); //o merge faz uma cópia da instancia produto e essa cópia é que é gerenciada pelo entityManager
        entityManager.getTransaction().commit();

        entityManager.clear(); // precisa limpar a memória para fazer a asserção por que o objeto ainda estará na memória

        Produto produtoVerificação = entityManager.find(Produto.class, 1);
        Assertions.assertNotNull(produtoVerificação);
        Assertions.assertEquals("Kindle Paperwhite", produtoVerificação.getNome());
    }

    @Test
    public void removerObjeto() {
//        Produto produto = new Produto();    //objeto não mapeado pelo JPA
//        produto.setId(3);   //gera o erro: java.lang.IllegalArgumentException: Removing a detached instance com.algaworks.ecommerce.model.Produto#3

//        Produto produto = new Produto();    //objeto não mapeado pelo JPA
//        produto.setId(300);   // Não gera erro pois ele não encontra o objeto no select que o JPA faz

        Produto produto = entityManager.find(Produto.class, 3);

//        entityManager.remove(produto);  // Não precisa estar dentro de uma transação, ele aguarda a próxima;
//        entityManager.getTransaction().begin();
//
//        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        entityManager.remove(produto);
        entityManager.getTransaction().commit();

//        entityManager.clear(); // não precisa limpar a memória para fazer a asserção por que após o remove ele é retirado da memória

        Produto produtoVerificação = entityManager.find(Produto.class, 3);
        Assertions.assertNull(produtoVerificação);
    }

    @Test
    public void inserirOPrimeiroObjeto(){
        Produto produto = new Produto();

        produto.setId(2);
        produto.setNome("Canon");
        produto.setDescricao("A melhor definição para suas fotos.");
        produto.setPreco(new BigDecimal(5000));

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();

        //o método find já estará com o objeto em memória não buscando no banco de dados caso não seja feita uma
        //das duas operações a seguir
        //entityManager.close();
        //entityManager = entityManagerFactory.createEntityManager();
        //ou
        entityManager.clear();
        ////// usuando um desses dois artifícios ele irá executar um select no banco. Caso contrario só fará o insert e pegará o valor da memória,

        Produto produtoVerificação = entityManager.find(Produto.class, produto.getId());
        Assertions.assertNotNull(produtoVerificação);
        System.out.println(produtoVerificação.toString());
    }


    @Test
    public void inserirOPrimeiroObjeto2(){
        Produto produto = new Produto();

        produto.setId(2);
        produto.setNome("Canon");
        produto.setDescricao("A melhor definição para suas fotos.");
        produto.setPreco(new BigDecimal(5000));

        //Ṕosso fazer o persist fora de uma transação que ele será executado quando a proxíma iniciar.
        //porém por uma questão de organização é recomendado que fique entre o begin e o commit.
        entityManager.persist(produto);
        // obriga o que está na memória ir paea o banco de dados, inclusive é uma das operações realizadas no commit logo ali abaixo.
        // caso o flush seja feito sem que haja  nenhuma transação gerará uma exceção deste tipo.
        //entityManager.flush();

        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificação = entityManager.find(Produto.class, produto.getId());
        Assertions.assertNotNull(produtoVerificação);
        System.out.println(produtoVerificação.toString());
    }

    @Test
    public void abrirEFecharTransacao(){
//        Produto produto =  new Produto();

        entityManager.getTransaction().begin();

//        entityManager.persist(produto);
//        entityManager.merge(produto);
//        entityManager.remove(produto);

        entityManager.getTransaction().commit();
    }
}
