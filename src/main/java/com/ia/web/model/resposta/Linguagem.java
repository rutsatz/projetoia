package com.ia.web.model.resposta;

public enum Linguagem {
	// descricao, peso
	JAVASCRIPT("JavaScript", new double[] { 0, 0, 0, 0 }), // 1
	JAVA("Java", new double[] { 0, 0, 0, 1 }), // 2
	CSS("CSS", new double[] { 0, 0, 1, 0 }), // 3
	PYTHON("Python", new double[] { 0, 0, 1, 1 }), // 4
	PHP("PHP", new double[] { 0, 1, 0, 0 }), // 5
	RUBY("Ruby", new double[] { 0, 1, 0, 1 }), // 6
	CPP("C++", new double[] { 0, 1, 1, 0 }), // 7
	C("C", new double[] { 0, 1, 1, 1 }), // 8
	SHELL("Shell", new double[] { 1, 0, 0, 0 }), // 9
	CSHARP("C#", new double[] { 1, 0, 0, 1 }); // 10

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
