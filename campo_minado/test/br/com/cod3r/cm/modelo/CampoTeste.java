package br.com.cod3r.cm.modelo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CampoTeste {
	
	private Campo campo;
	
	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3, 3);
	}

	@Test
	void testeVizinhoReal() {
		Campo vizinho = new Campo(3, 2);
		
		boolean teste1 = campo.adicionarVizinho(vizinho);
		
		assertTrue(teste1);
		
	}
}
