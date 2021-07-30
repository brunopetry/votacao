package com.example.votacao.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "voto")
public class Voto {

	@EmbeddedId
	private VotoPK id;

	private String voto;

	@ManyToOne
	@JoinColumn(name = "pauta_id", insertable = false, updatable = false)
	@JsonIgnore
	private Pauta pauta;

	public Voto() {
	}

	public Voto(VotoPK id, String voto) {
		super();
		this.id = id;
		this.voto = voto;
	}

	public VotoPK getId() {
		return id;
	}

	public void setId(VotoPK id) {
		this.id = id;
	}

	public Pauta getPauta() {
		return pauta;
	}

	public void setPauta(Pauta pauta) {
		this.pauta = pauta;
	}

	public String getVoto() {
		return voto;
	}

	public void setVoto(String voto) {
		this.voto = voto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Voto other = (Voto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Voto [id=" + id + ", voto=" + voto + "]";
	}

}
