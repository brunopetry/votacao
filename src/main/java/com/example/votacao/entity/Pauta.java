package com.example.votacao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "America/Sao_Paulo")
	private Date dataCriacao;

	@Transient
	private boolean aberta;

	@Column(name = "dataabertura")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "America/Sao_Paulo")
	private Date dataAbertura;

	@Column(name = "datafechamento")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "America/Sao_Paulo")
	private Date dataFechamento;

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

	public boolean isAberta() {
		
//		if (getDataAbertura() == null || getDataFechamento() == null) {
//			return false;
//		}
		
		if (getDataAbertura() != null && getDataFechamento() != null && getDataFechamento().before(new Date())) {
			return true;
		}

		return false;
		
//		return aberta;
	}

//	public void setAberta(boolean aberta) {
//		this.aberta = aberta;
//	}

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
