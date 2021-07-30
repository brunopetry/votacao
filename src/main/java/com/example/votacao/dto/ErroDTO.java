package com.example.votacao.dto;

import java.time.LocalDateTime;

public class ErroDTO {

	private LocalDateTime data;
	private String mensagemErro;

	public ErroDTO() {
	}

	public ErroDTO(String mensagemErro) {
		this.data = LocalDateTime.now();
		this.mensagemErro = mensagemErro;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

}
