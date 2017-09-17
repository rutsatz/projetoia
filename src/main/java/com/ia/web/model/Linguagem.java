package com.ia.web.model;

public enum Linguagem {
	// descricao, peso
	JAVA("Java", new double[]{0,0,0}),
	PHP("PHP", new double[]{0,0,1}),
	CPP("C++", new double[]{0,1,0});
	
	private String descricao;
	
	private double[] peso;
	
	private Linguagem(String descricao, double[] peso) {
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
