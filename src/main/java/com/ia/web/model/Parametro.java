package com.ia.web.model;

import java.math.BigInteger;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * Parâmetros de configuração da RNA.
 * @author Rafael
 *
 */

public class Parametro {
	
	@NotNull( message = "Informe a quantidade de camadas intermediárias!")
	private Integer qtdCamadasIntermediarias;
	
	@NotNull(message = "Obrigatório informar a quantidade de neurônios por camada!")
	@Size(min = 1, message = "É obrigatório informar pelo menos um neurônio por camda!")
	private String neuroniosPorCamada;
	
	@NotNull(message = "Taxa de aprendizagem não informada!")
	@DecimalMin(value = "0.001", message = "Taxa de aprendizagem mínima de 0,001!")
	@DecimalMax(value = "9.99", message = "Taxa de aprendizagem acima do limite permitido!")
	@NumberFormat(pattern = "#,##0.000")
	private Double taxaAprendizagem;
	
	@NotNull(message = "Erro aceitável não informado!")
	@DecimalMin(value = "0.001", message = "Erro aceitável mínimo é 0,001!")
	@DecimalMax(value = "9.99", message = "Erro aceitável acima do limite permitido!")
	@NumberFormat(pattern = "#,##0.000")
	private Double erroAceitavel;
	
	@NotNull(message = "Quantidade de iterações não informada!")
	@Min(value = 1, message = "Quantidade mínima de iterações é 1!")
	@Max(value = 10000000, message = "Quantidade máxima de iterações é 10 milhões!")
	@NumberFormat(pattern = "0")
	private BigInteger qtdIteracoes;

	private Boolean usarArquivo;
	
	public Parametro(Integer qtdCamadasIntermediarias, String neuroniosPorCamada, Double taxaAprendizagem,
			Double erroAceitavel, BigInteger qtdIteracoes, Boolean usarArquivo) {
		
		this.qtdCamadasIntermediarias = qtdCamadasIntermediarias;
		this.neuroniosPorCamada = neuroniosPorCamada;
		this.taxaAprendizagem = taxaAprendizagem;
		this.erroAceitavel = erroAceitavel;
		this.qtdIteracoes = qtdIteracoes;
		this.usarArquivo = usarArquivo;
	}

	public Integer getQtdCamadasIntermediarias() {
		return qtdCamadasIntermediarias;
	}

	public void setQtdCamadasIntermediarias(Integer qtdCamadasIntermediarias) {
		this.qtdCamadasIntermediarias = qtdCamadasIntermediarias;
	}

	public String getNeuroniosPorCamada() {
		return neuroniosPorCamada;
	}

	public void setNeuroniosPorCamada(String neuroniosPorCamada) {
		this.neuroniosPorCamada = neuroniosPorCamada;
	}

	public Double getTaxaAprendizagem() {
		return taxaAprendizagem;
	}

	public void setTaxaAprendizagem(Double taxaAprendizagem) {
		this.taxaAprendizagem = taxaAprendizagem;
	}

	public Double getErroAceitavel() {
		return erroAceitavel;
	}

	public void setErroAceitavel(Double erroAceitavel) {
		this.erroAceitavel = erroAceitavel;
	}

	public BigInteger getQtdIteracoes() {
		return qtdIteracoes;
	}

	public void setQtdIteracoes(BigInteger qtdIteracoes) {
		this.qtdIteracoes = qtdIteracoes;
	}

	public Boolean getUsarArquivo() {
		return usarArquivo;
	}

	public void setUsarArquivo(Boolean usarArquivo) {
		this.usarArquivo = usarArquivo;
	}
	
	
}
