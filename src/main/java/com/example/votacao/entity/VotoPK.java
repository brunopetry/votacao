package com.example.votacao.entity;

import java.io.Serializable;

import javax.persistence.Column;

import org.hibernate.validator.constraints.br.CPF;

public class VotoPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@CPF
	private String cpf;

	@Column(name = "pauta_id")
	private int pautaId;

	public VotoPK() {
	}

	public VotoPK(@CPF String cpf, int pautaId) {
		super();
		this.cpf = cpf;
		this.pautaId = pautaId;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getPautaId() {
		return pautaId;
	}

	public void setPautaId(int pautaId) {
		this.pautaId = pautaId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + pautaId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VotoPK other = (VotoPK) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (pautaId != other.pautaId)
			return false;
		return true;
	}

}
