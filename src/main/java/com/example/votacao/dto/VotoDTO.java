package com.example.votacao.dto;

import com.example.votacao.util.ValidarCPF;

public class VotoDTO {

	private String cpf;
	private int idPauta;
	private String voto;

	public int getIdPauta() {
		return idPauta;
	}

	public void setIdPauta(int idPauta) {
		this.idPauta = idPauta;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {

		if (cpf != null) {
			cpf = ValidarCPF.removeCaracteresEspeciais(cpf);
		}

		this.cpf = cpf;
	}

	public String getVoto() {
		return voto;
	}

	public void setVoto(String voto) {
		this.voto = voto;
	}

}
