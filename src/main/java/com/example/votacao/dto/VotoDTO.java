package com.example.votacao.dto;

import com.example.votacao.entity.Voto;

public class VotoDTO {

	private int idPauta;
	private Voto voto;

	public int getIdPauta() {
		return idPauta;
	}

	public void setIdPauta(int idPauta) {
		this.idPauta = idPauta;
	}

	public Voto getVoto() {
		return voto;
	}

	public void setVoto(Voto voto) {
		this.voto = voto;
	}

}
