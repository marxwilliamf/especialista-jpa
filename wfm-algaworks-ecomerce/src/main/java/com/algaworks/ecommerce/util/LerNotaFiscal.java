package com.algaworks.ecommerce.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LerNotaFiscal {

	public static byte[]  carregarNotaFiscal() {
		try {
			return LerNotaFiscal.class.getResourceAsStream("/nota-fiscal.xml").readAllBytes();
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void salvaArquivo(byte[] arquivo) {
		try {
			OutputStream out = new FileOutputStream(Files.createFile(Paths.get(System.getProperty("user.home") + "/xml.xml")).toFile());
			out.write(arquivo);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
