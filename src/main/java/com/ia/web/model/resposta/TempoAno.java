package com.ia.web.model.resposta;

public enum TempoAno {
	
	UM_ANO_MENOS("1 ano ou menos",new double[]{0,0,0}), // 1
	DOIS_ANOS("2 anos",new double[]{0,0,1}), // 2
	TRES_ANOS("3 anos",new double[]{0,1,0}), // 3
	QUATRO_ANOS("4 anos",new double[]{0,1,1}), // 4
	CINCO_ANOS_MAIS("5 anos ou mais",new double[]{1,0,0}); // 5

	private String descricao;
	
	private double[] peso;
	
	private TempoAno(String descricao, double[] peso) {
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
