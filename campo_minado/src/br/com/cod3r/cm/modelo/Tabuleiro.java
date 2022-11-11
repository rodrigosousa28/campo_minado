package br.com.cod3r.cm.modelo;

import java.util.ArrayList;
import java.util.function.Predicate;

import br.com.cod3r.cm.excecao.ExplosaoException;

public class Tabuleiro {

	private int quantidadeLinhas;
	private int quantidadeColunas;
	private int quantidadeMinas;
	
	private final ArrayList<Campo> campos = new ArrayList<>();

	public Tabuleiro(int quantidadeLinhas, int quantidadeColunas, int quantidadeMinas) {
		this.quantidadeLinhas = quantidadeLinhas;
		this.quantidadeColunas = quantidadeColunas;
		this.quantidadeMinas = quantidadeMinas;
		
		gerarCampos();
		associarVizinhos();
		sortearMinas();
	}
	
	public void abrir(int linha, int coluna) {
		try {
			campos.parallelStream()
			.filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
			.findFirst()
			.ifPresent(c -> c.abrir());
		} catch (ExplosaoException e) {
			campos.stream().filter(c -> c.minado).forEach(c -> c.setAberto(true));
			throw e;
		}
	}
	public void alternarMarcacao(int linha, int coluna) {
		campos.parallelStream()
		.filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
		.findFirst()
		.ifPresent(c -> c.alternarMarcacao());;
	}

	private void gerarCampos() {
		for(int linha = 0; linha < quantidadeLinhas; linha++) {
			for(int coluna = 0; coluna < quantidadeColunas; coluna++) {
				campos.add(new Campo(linha, coluna));
			}
		}
	}
	
	private void associarVizinhos() {
		for(Campo c1: campos) {
			for(Campo c2: campos) {
				c1.adicionarVizinho(c2);
			}
		}
	}
	
	private void sortearMinas() {
		long quantidadeMinas = 0;
		Predicate<Campo> minado = c -> c.minado;
		
		do {
			quantidadeMinas = campos.stream().filter(minado).count();			
			int aleatorio = (int) (Math.random() * campos.size()); 
			campos.get(aleatorio).minar();
		}while(quantidadeMinas < this.quantidadeMinas);
	}
	
	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(c -> c.objetivoAlcancado());
	}
	
	public void reiniciar() {
		campos.stream().forEach(c -> c.reiniciar());
		sortearMinas();
	}
	
	public String toString() {
		String sb = "";
		int i = 0;
		
		for(int l = 0; l < quantidadeLinhas; l++) {
			for(int c = 0; c < quantidadeColunas; c++) {
				sb += " ";
				sb += campos.get(i);
				sb += " ";
				i++;
			}
			sb += "\n";
		}
		
		return sb;
	}
}
