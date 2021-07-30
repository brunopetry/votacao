package com.example.votacao.dto;

import java.util.Date;

public class AbrirSessaoVotacaoDTO {

	private int idPauta;
	private Date dataAbertura;
	private Date dataFechamento;

	public int getIdPauta() {
		return idPauta;
	}

	public void setIdPauta(int idPauta) {
		this.idPauta = idPauta;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

}
