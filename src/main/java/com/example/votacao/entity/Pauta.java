package com.example.votacao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity(name = "pauta")
public class Pauta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	@NotNull
	private String nome;

	@Column(name = "datacriacao")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date dataCriacao;
	
	public Pauta() {
	}

	public Pauta(@NotNull String nome, @NotNull Date dataCriacao) {
		super();
		this.nome = nome;
		this.dataCriacao = dataCriacao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

}
