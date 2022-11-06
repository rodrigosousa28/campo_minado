package br.com.cod3r.cm.modelo;

import java.util.ArrayList;
import java.util.List;

import br.com.cod3r.cm.excecao.ExplosaoException;

public class Campo {

	private final int linha;
	private final int coluna;
	
	private boolean aberto = false;
	private boolean minado = false;
	private boolean marcado = false;
	
	private List<Campo> vizinhos = new ArrayList<>();
	
	Campo(int linha, int coluna){
		this.linha = linha;
		this.coluna = coluna;
	}
	
	boolean adicionarVizinho(Campo vizinho) {
		
		int distanciaLinhas = Math.abs(this.linha - vizinho.linha);
		int distanciaColunas = Math.abs(this.coluna - vizinho.coluna);
		int distancia = distanciaLinhas + distanciaColunas;
		
		if(distancia == 1) {
			vizinhos.add(vizinho);
			return true;
		}else if (distanciaLinhas == 1 && distanciaColunas == 1) {
			vizinhos.add(vizinho);
			return true;
		}
		return false;
	}
	
	void alternarMarcacao() {
		if(!aberto) {
			marcado = !marcado;
		}
	}
	
	boolean abrir() {
		if(!aberto && !marcado) {
			aberto = true;
			
			if(minado) {
				throw new ExplosaoException();
			}
			if(vizinhancaSegura()) {
				vizinhos.forEach(v -> v.abrir());
			}
			
			return true;
		}
		return false;
	}
	
	boolean vizinhancaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}
}













