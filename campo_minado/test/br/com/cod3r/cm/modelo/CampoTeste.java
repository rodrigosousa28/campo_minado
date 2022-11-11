package br.com.cod3r.cm.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.cod3r.cm.excecao.ExplosaoException;

public class CampoTeste {
	
	private Campo campo;
	
	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3, 3);
	}

	@Test
	void testeVizinhoOeste() {
		Campo vizinho = new Campo(3, 2);
		boolean teste1 = campo.adicionarVizinho(vizinho);
		assertTrue(teste1);
	}
	@Test
	void testeVizinhoLeste() {
		Campo vizinho = new Campo(3, 4);
		boolean teste1 = campo.adicionarVizinho(vizinho);
		assertTrue(teste1);
	}
	@Test
	void testeVizinhoNorte() {
		Campo vizinho = new Campo(2, 3);
		boolean teste1 = campo.adicionarVizinho(vizinho);
		assertTrue(teste1);
	}
	@Test
	void testeVizinhoSul() {
		Campo vizinho = new Campo(4, 3);
		boolean teste1 = campo.adicionarVizinho(vizinho);
		assertTrue(teste1);
	}
	@Test
	void testeVizinhoNordeste() {
		Campo vizinho = new Campo(2, 4);
		boolean teste1 = campo.adicionarVizinho(vizinho);
		assertTrue(teste1);
	}
	@Test
	void testeVizinhoNoroeste() {
		Campo vizinho = new Campo(2, 2);
		boolean teste1 = campo.adicionarVizinho(vizinho);
		assertTrue(teste1);
	}
	@Test
	void testeVizinhoSudeste() {
		Campo vizinho = new Campo(4, 4);
		boolean teste1 = campo.adicionarVizinho(vizinho);
		assertTrue(teste1);
	}
	@Test
	void testeVizinhoSudoeste() {
		Campo vizinho = new Campo(4, 2);
		boolean teste1 = campo.adicionarVizinho(vizinho);
		assertTrue(teste1);
	}
	@Test
	void testeNaoVizinho() {
		Campo vizinho = new Campo(4, 5);
		boolean teste1 = campo.adicionarVizinho(vizinho);
		assertFalse(teste1);
	}
	
	@Test
	void testeValorPadraoAtributoMarcado() {
		assertFalse(campo.isMarcado());
	}
	@Test
	void testeAlternarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}
	@Test
	void testeAlternarMarcacaoDuasChamadas() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}
	@Test
	void testeAbrirNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
	}
	@Test
	void testeAbrirNaoMinadoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	@Test
	void testeAbrirMinadoMarcado() {
		campo.minar();
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	@Test
	void testeAbrirMinadoNaoMarcado() {
		campo.minar();
		assertThrows(ExplosaoException.class, () -> campo.abrir());
	}
	@Test
	void abrirComVizinho1() {
		Campo campo11 = new Campo(1, 1);
		Campo campo22 = new Campo(2, 2);
		campo22.adicionarVizinho(campo11);
		
		campo.adicionarVizinho(campo22);
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isAberto());
	}
	@Test
	void abrirComVizinho2(){
		Campo campo11 = new Campo(1, 1);
		Campo campo12 = new Campo(1, 2);
		campo12.minar();
		
		Campo campo22 = new Campo(2, 2);
		campo22.adicionarVizinho(campo11);
		campo22.adicionarVizinho(campo12);
		
		campo.adicionarVizinho(campo22);
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isFechado());
	}
}
