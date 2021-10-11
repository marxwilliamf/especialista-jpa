package com.algaworks.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;

public class EntendendoJUnitTest {

    @BeforeAll
    public static void iniciaTestes(){
        System.out.println(">>> iniciaTestes() <<<");
    }

    @AfterAll
    public static void encerrarTestes(){
        System.out.println(">>> encerrarTestes() <<<");
    }

    @BeforeEach
    public void iniciaTeste(){
        System.out.println(">>> iniciaTeste() <<<");
    }

    @AfterEach
    public void encerrarTeste(){
        System.out.println(">>> encerrarTeste() <<<");
    }

    @Test
    public void testandoAlgo(){
        System.out.println(">>> testandoAlgo() <<<");
        String nome = String.format("%s", "William");
        Assertions.assertEquals("William", nome);
    }

    @Test
    public void testandoOutraCoisa(){
        System.out.println(">>> testandoOutraCoisa() <<<");
        String str = String.format("%s", "");

        Assertions.assertTrue(str.isEmpty());
    }
}
