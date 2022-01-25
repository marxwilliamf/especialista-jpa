package com.algaworks.ecommerce.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Arquivos {

	public static byte[]  leArquivo(String nome) {
		try {
			return Arquivos.class.getResourceAsStream(nome).readAllBytes();
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void salvaArquivo(byte[] arquivo, String nome) {
		try {
			OutputStream out = new FileOutputStream(Files.createFile(Paths.get(System.getProperty("user.home") + nome)).toFile());
			out.write(arquivo);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
