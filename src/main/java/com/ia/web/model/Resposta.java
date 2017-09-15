package com.ia.web.model;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ia.web.model.resposta.Dificuldade;

/**
 * Salva os resultados do questionário no banco de dados.
 * 
 * @author Rafael
 *
 */
@Entity
public class Resposta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Embedded
	private BackPropagation backPropagation;

	@Enumerated(EnumType.STRING)
	private Dificuldade resultado;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	public Resposta(BackPropagation backPropagation, Dificuldade resultado) {
		super();
		this.backPropagation = backPropagation;
		this.resultado = resultado;
		this.data = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BackPropagation getBackPropagation() {
		return backPropagation;
	}

	public void setBackPropagation(BackPropagation backPropagation) {
		this.backPropagation = backPropagation;
	}

	public Dificuldade getResultado() {
		return resultado;
	}

	public void setResultado(Dificuldade resultado) {
		this.resultado = resultado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((backPropagation == null) ? 0 : backPropagation.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((resultado == null) ? 0 : resultado.hashCode());
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
		Resposta other = (Resposta) obj;
		if (backPropagation == null) {
			if (other.backPropagation != null)
				return false;
		} else if (!backPropagation.equals(other.backPropagation))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (resultado != other.resultado)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Resposta [id=");
		builder.append(id);
		builder.append(", backPropagation=");
		builder.append(backPropagation);
		builder.append(", resultado=");
		builder.append(resultado);
		builder.append(", data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}

}
