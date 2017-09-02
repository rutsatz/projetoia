package com.ia.web.model;

import java.io.File;
import java.math.BigInteger;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class BackPropagation {

	@Valid
	private Parametro parametro;

	private Linguagem primeiraLinguagem;
	
	private Linguagem novaLinguagem;
	
	private Aprendizado dificuldadeAprendizado;

	private File arquivoTreinamento;
	
	public BackPropagation() {
		// Define parâmetros default quando nenhum parâmetro
		// for definido.
		this.parametro = new Parametro(1, // Qtd camadas intermediárias.
				"10,5,10", // Qtd neurônios por camada.
				0.3, // Taxa de aprendizagem.
				0.005, // Erro aceitável.
				new BigInteger("500000"), // Número de iterações.
				false
		);

		this.primeiraLinguagem = Linguagem.JAVA;
		
		this.novaLinguagem = Linguagem.JAVA;
		
		this.dificuldadeAprendizado = Aprendizado.FACILIDADE;
	}

	public BackPropagation(Parametro parametro) {
		super();
		this.parametro = parametro;
	}

	public Parametro getParametro() {
		return parametro;
	}

	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}

	public Linguagem getPrimeiraLinguagem() {
		return primeiraLinguagem;
	}

	public void setPrimeiraLinguagem(Linguagem primeiraLinguagem) {
		this.primeiraLinguagem = primeiraLinguagem;
	}

	public Linguagem getNovaLinguagem() {
		return novaLinguagem;
	}

	public void setNovaLinguagem(Linguagem novaLinguagem) {
		this.novaLinguagem = novaLinguagem;
	}

	public Aprendizado getDificuldadeAprendizado() {
		return dificuldadeAprendizado;
	}

	public void setDificuldadeAprendizado(Aprendizado dificuldadeAprendizado) {
		this.dificuldadeAprendizado = dificuldadeAprendizado;
	}

	public File getArquivoTreinamento() {
		return arquivoTreinamento;
	}

	public void setArquivoTreinamento(File arquivoTreinamento) {
		this.arquivoTreinamento = arquivoTreinamento;
	}


}
