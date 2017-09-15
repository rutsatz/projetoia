package com.ia.web.model;

import java.math.BigInteger;
import java.util.regex.Pattern;

import javax.persistence.Embeddable;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

/**
 * Parâmetros de configuração da RNA.
 * 
 * @author Rafael
 *
 */
@Embeddable
public class Parametro {

	@NotNull(message = "Informe a quantidade de camadas intermediárias!")
	private int qtdCamadasIntermediarias;

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

	/**
	 * Quantidade de neurônios da camada de entrada.
	 */
	private int inputLayerSize;

	/**
	 * Quantidade de neurônios da camada de saída.
	 */
	private int ouputLayerSize;

	public Parametro() {
	}

	public Parametro(int qtdCamadasIntermediarias, String neuroniosPorCamada, Double taxaAprendizagem,
			Double erroAceitavel, BigInteger qtdIteracoes, Boolean usarArquivo, int inputLayerSize,
			int ouputLayerSize) {

		this.qtdCamadasIntermediarias = qtdCamadasIntermediarias;
		this.neuroniosPorCamada = neuroniosPorCamada;
		this.taxaAprendizagem = taxaAprendizagem;
		this.erroAceitavel = erroAceitavel;
		this.qtdIteracoes = qtdIteracoes;
		this.usarArquivo = usarArquivo;
		this.inputLayerSize = inputLayerSize;
		this.ouputLayerSize = ouputLayerSize;
	}

	/**
	 * Converte a string das camadas separadas por vírgula num vetor.
	 * 
	 * @return
	 */
	public int[] getNeuroniosPorCamadaVetor() {
		String str = getNeuroniosPorCamada();
		String camadasStr[] = str.split(Pattern.quote(","));

		int camadas[] = new int[camadasStr.length];

		for (int i = 0; i < camadasStr.length; i++) {
			camadas[i] = Integer.parseInt(camadasStr[i]);
		}
		return camadas;
	}

	public int getQtdCamadasIntermediarias() {
		return qtdCamadasIntermediarias;
	}

	public void setQtdCamadasIntermediarias(int qtdCamadasIntermediarias) {
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

	public int getInputLayerSize() {
		return inputLayerSize;
	}

	public void setInputLayerSize(int inputLayerSize) {
		this.inputLayerSize = inputLayerSize;
	}

	public int getOuputLayerSize() {
		return ouputLayerSize;
	}

	public void setOuputLayerSize(int ouputLayerSize) {
		this.ouputLayerSize = ouputLayerSize;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((erroAceitavel == null) ? 0 : erroAceitavel.hashCode());
		result = prime * result + inputLayerSize;
		result = prime * result + ((neuroniosPorCamada == null) ? 0 : neuroniosPorCamada.hashCode());
		result = prime * result + ouputLayerSize;
		result = prime * result + qtdCamadasIntermediarias;
		result = prime * result + ((qtdIteracoes == null) ? 0 : qtdIteracoes.hashCode());
		result = prime * result + ((taxaAprendizagem == null) ? 0 : taxaAprendizagem.hashCode());
		result = prime * result + ((usarArquivo == null) ? 0 : usarArquivo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parametro other = (Parametro) obj;
		if (erroAceitavel == null) {
			if (other.erroAceitavel != null)
				return false;
		} else if (!erroAceitavel.equals(other.erroAceitavel))
			return false;
		if (inputLayerSize != other.inputLayerSize)
			return false;
		if (neuroniosPorCamada == null) {
			if (other.neuroniosPorCamada != null)
				return false;
		} else if (!neuroniosPorCamada.equals(other.neuroniosPorCamada))
			return false;
		if (ouputLayerSize != other.ouputLayerSize)
			return false;
		if (qtdCamadasIntermediarias != other.qtdCamadasIntermediarias)
			return false;
		if (qtdIteracoes == null) {
			if (other.qtdIteracoes != null)
				return false;
		} else if (!qtdIteracoes.equals(other.qtdIteracoes))
			return false;
		if (taxaAprendizagem == null) {
			if (other.taxaAprendizagem != null)
				return false;
		} else if (!taxaAprendizagem.equals(other.taxaAprendizagem))
			return false;
		if (usarArquivo == null) {
			if (other.usarArquivo != null)
				return false;
		} else if (!usarArquivo.equals(other.usarArquivo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Parametro [qtdCamadasIntermediarias=");
		builder.append(qtdCamadasIntermediarias);
		builder.append(", neuroniosPorCamada=");
		builder.append(neuroniosPorCamada);
		builder.append(", taxaAprendizagem=");
		builder.append(taxaAprendizagem);
		builder.append(", erroAceitavel=");
		builder.append(erroAceitavel);
		builder.append(", qtdIteracoes=");
		builder.append(qtdIteracoes);
		builder.append(", usarArquivo=");
		builder.append(usarArquivo);
		builder.append(", inputLayerSize=");
		builder.append(inputLayerSize);
		builder.append(", ouputLayerSize=");
		builder.append(ouputLayerSize);
		builder.append("]");
		return builder.toString();
	}

}
