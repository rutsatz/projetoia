package com.ia.web.model.resposta;

public enum SimNao {

	SIM("Sim", new double[] { 0, 0 }), // 1
	NAO("Não", new double[] { 0, 1 }); // 2

	private String descricao;

	private double[] peso;

	private SimNao(String descricao, double[] peso) {
		this.descricao = descricao;
		this.peso = peso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double[] getPeso() {
		return peso;
	}

	public void setPeso(double[] peso) {
		this.peso = peso;
	}

}
