package com.ia.web.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ia.web.model.resposta.Dificuldade;
import com.ia.web.model.resposta.Linguagem;

/**
 * Representa os dados na tela de estatística.
 * 
 * @author Rafael
 *
 */
public class Estatistica {

	@Enumerated(EnumType.STRING)
	private Linguagem primeiraLinguagem;

	@Enumerated(EnumType.STRING)
	private Linguagem segundaLinguagem;

	@Enumerated(EnumType.STRING)
	private Dificuldade resultado;

	private Long quantidade;

	private double porcentagem;

	public Estatistica() {
	}

	public Estatistica(Linguagem primeiraLinguagem, Linguagem segundaLinguagem, Dificuldade resultado,
			Long quantidade) {
		super();
		this.primeiraLinguagem = primeiraLinguagem;
		this.segundaLinguagem = segundaLinguagem;
		this.resultado = resultado;
		this.quantidade = quantidade;
	}

	public String getPrimeiraLinguagem() {
		return primeiraLinguagem.getDescricao();
	}

	public void setPrimeiraLinguagem(Linguagem primeiraLinguagem) {
		this.primeiraLinguagem = primeiraLinguagem;
	}

	public String getSegundaLinguagem() {
		return segundaLinguagem.getDescricao();
	}

	public void setSegundaLinguagem(Linguagem segundaLinguagem) {
		this.segundaLinguagem = segundaLinguagem;
	}

	public String getResultado() {
		return resultado.getDescricao();
	}

	public void setResultado(Dificuldade resultado) {
		this.resultado = resultado;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public double getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(long total) {
		this.porcentagem = Math.round((quantidade * 100) / total);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(porcentagem);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((primeiraLinguagem == null) ? 0 : primeiraLinguagem.hashCode());
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
		result = prime * result + ((resultado == null) ? 0 : resultado.hashCode());
		result = prime * result + ((segundaLinguagem == null) ? 0 : segundaLinguagem.hashCode());
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
		Estatistica other = (Estatistica) obj;
		if (Double.doubleToLongBits(porcentagem) != Double.doubleToLongBits(other.porcentagem))
			return false;
		if (primeiraLinguagem != other.primeiraLinguagem)
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		if (resultado != other.resultado)
			return false;
		if (segundaLinguagem != other.segundaLinguagem)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Estatistica [primeiraLinguagem=");
		builder.append(primeiraLinguagem);
		builder.append(", segundaLinguagem=");
		builder.append(segundaLinguagem);
		builder.append(", resultado=");
		builder.append(resultado);
		builder.append(", quantidade=");
		builder.append(quantidade);
		builder.append(", porcentagem=");
		builder.append(porcentagem);
		builder.append("]");
		return builder.toString();
	}

}
