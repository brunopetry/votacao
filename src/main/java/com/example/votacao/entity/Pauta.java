package com.example.votacao.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
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
	@NotNull
	private LocalDateTime dataCriacao;

	@Transient
	private boolean aberta;

	@Column(name = "dataabertura")
	private LocalDateTime dataAbertura;

	@Column(name = "datafechamento")
	private LocalDateTime dataFechamento;

	public Pauta() {
	}

	public Pauta(@NotNull String nome) {
		super();
		this.nome = nome;
		this.dataCriacao = LocalDateTime.now();
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
	
	public boolean isAberta() {
		return getDataAbertura() != null && getDataFechamento() != null && LocalDateTime.now().isBefore(getDataFechamento());
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	

}
