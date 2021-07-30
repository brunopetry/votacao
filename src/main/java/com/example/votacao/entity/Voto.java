package com.example.votacao.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "voto")
public class Voto {

	@Id
	@CPF
	private String cpf;

	@Enumerated(EnumType.STRING)
	private Tipo voto;

	@ManyToOne
	@JsonIgnore
	private Pauta pauta;

	public Voto() {
	}

	public Voto(@CPF String cpf, Tipo voto, Pauta pauta) {
		super();
		this.cpf = cpf;
		this.voto = voto;
		this.pauta = pauta;
	}

	public enum Tipo {
		SIM("SIM"), NAO("NÃO");

		private String tipo;

		private Tipo(String tipo) {
			this.tipo = tipo;
		}

		@Override
		public String toString() {
			return this.tipo;
		}
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Tipo getVoto() {
		return voto;
	}

	public void setVoto(Tipo voto) {
		this.voto = voto;
	}

}
