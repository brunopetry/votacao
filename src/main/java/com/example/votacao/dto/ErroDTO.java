package com.example.votacao.dto;

import java.util.Date;

public class ErroDTO {

	private Date data;
	private String mensagemErro;

	public ErroDTO() {
	}

	public ErroDTO(String mensagemErro) {
		this.data = new Date();
		this.mensagemErro = mensagemErro;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getMensagem() {
		return mensagemErro;
	}

	public void setMensagem(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

}
