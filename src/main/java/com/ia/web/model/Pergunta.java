package com.ia.web.model;

/**
 * Representa uma pergunta do usuário.
 * @author Rafael
 * @param <T>
 *
 */
public class Pergunta {
	
	private String descricao;
	
	private Enum opcao;

	public Pergunta(String descricao, Enum opcao) {
		
		this.descricao = descricao;
		this.opcao = opcao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Enum getOpcao() {
		return opcao;
	}

	public void setOpcao(Enum opcao) {
		this.opcao = opcao;
	}



	
}
