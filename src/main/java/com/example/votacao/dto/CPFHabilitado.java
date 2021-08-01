package com.example.votacao.dto;

public class CPFHabilitado {

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isHabilitado() {
		return status.equalsIgnoreCase("ABLE_TO_VOTE");
	}

}
