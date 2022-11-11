package br.com.cod3r.cm.modelo;

import java.util.ArrayList;
import java.util.List;

import br.com.cod3r.cm.excecao.ExplosaoException;

public class Campo {

	private final int linha;
	private final int coluna;
	
	private boolean aberto = false;
	boolean minado = false;
	private boolean marcado = false;
	
	private List<Campo> vizinhos = new ArrayList<>();
	
	Campo(int linha, int coluna){
		this.linha = linha;
		this.coluna = coluna;
	}
	
	public int getLinha() {
		return linha;
	}
	public int getColuna() {
		return coluna;
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
	
	public boolean isMarcado() {
		return marcado;
	}
	
	public boolean isAberto(){
		return aberto;
	}
	
	public boolean isFechado() {
		return !isAberto();
	}
	
	void minar() {
		minado = true;
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
	
	boolean objetivoAlcancado() {
		boolean desvendado = !minado && aberto;
		boolean protegido = minado && marcado;
		return desvendado || protegido;
	}
	
	long minasNaVizinhanca() {
		return vizinhos.stream().filter(vizinho -> vizinho.minado).count();
	}
	
	void reiniciar() {
		aberto = false;
		minado = false;
		marcado = false;
	}
	
	public String toString() {
		if(marcado)
			return "x";
		else if(aberto && minado)
			return "*";
		else if(aberto && minasNaVizinhanca() > 0)
			return Long.toString(minasNaVizinhanca());
		else if(aberto)
			return " ";
		return "?";
	}

	public void setAberto(boolean aberto) {
		this.aberto = aberto;
	}
}













