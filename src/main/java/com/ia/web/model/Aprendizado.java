package com.ia.web.model;

public enum Aprendizado {
	FACILIDADE("Facilidade",new double[] {0,0}),
	MODERADO("Moderado", new double[] {0,1}),
	DIFICULDADE("Dificuldade", new double[] {1,0});
	
	private String descricao;
	
	private double[] peso;

	private Aprendizado(String descricao, double[] peso) {
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
