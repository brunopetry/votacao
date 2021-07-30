package com.example.votacao.dto;

public class ResultadoDTO {

	private int qtdVotosNao;

	private int qtdVotosSim;

	private String resultado;

	public ResultadoDTO() {
	}

	public ResultadoDTO(int qtdVotosNao, int qtdVotosSim, String resultado) {
		super();
		this.qtdVotosNao = qtdVotosNao;
		this.qtdVotosSim = qtdVotosSim;
		this.resultado = resultado;
	}

	public int getQtdVotosNao() {
		return qtdVotosNao;
	}

	public void setQtdVotosNao(int qtdVotosNao) {
		this.qtdVotosNao = qtdVotosNao;
	}

	public int getQtdVotosSim() {
		return qtdVotosSim;
	}

	public void setQtdVotosSim(int qtdVotosSim) {
		this.qtdVotosSim = qtdVotosSim;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

}
