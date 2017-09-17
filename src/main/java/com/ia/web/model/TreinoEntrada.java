package com.ia.web.model;

import com.ia.web.model.resposta.Linguagem;
import com.ia.web.model.resposta.SimNao;
import com.ia.web.model.resposta.TempoAno;

/**
 * Representa as entradas da minha RNA.
 * 
 * Linguagem 1: Minha primeira linguagem. Linguagem 2: Minha segunda linguagem.
 * 
 * @author Rafael
 *
 */
public class TreinoEntrada {

	private Linguagem primeiraLinguagem;

	private Linguagem segundaLinguagem;

	private TempoAno tempoAprendizadoAlgoritmos;

	private TempoAno tempoExperienciaProgramacao;

	private TempoAno tempoSemProgramar;

	private SimNao trabalhaComProgramacao;

	private SimNao usaOrientacaoObjetos;

	private SimNao usaProgramacaoWeb;

	private SimNao dificuldadeConcentracao;

	public TreinoEntrada(Linguagem primeiraLinguagem, Linguagem segundaLinguagem, TempoAno tempoAprendizadoAlgoritmos,
			TempoAno tempoExperienciaProgramacao, TempoAno tempoSemProgramar, SimNao trabalhaComProgramacao,
			SimNao usaOrientacaoObjetos, SimNao usaProgramacaoWeb, SimNao dificuldadeConcentracao) {
		super();
		this.primeiraLinguagem = primeiraLinguagem;
		this.segundaLinguagem = segundaLinguagem;
		this.tempoAprendizadoAlgoritmos = tempoAprendizadoAlgoritmos;
		this.tempoExperienciaProgramacao = tempoExperienciaProgramacao;
		this.tempoSemProgramar = tempoSemProgramar;
		this.trabalhaComProgramacao = trabalhaComProgramacao;
		this.usaOrientacaoObjetos = usaOrientacaoObjetos;
		this.usaProgramacaoWeb = usaProgramacaoWeb;
		this.dificuldadeConcentracao = dificuldadeConcentracao;
	}

	/**
	 * Retorna os dados no formato definido para a RNA.
	 * 
	 * Obs: No Arraycopy, quando o tamanho a ser copiado diminui, tenho que
	 * compensar na posição de inicio da cópia. Não sei porque, somente espero
	 * que funcione.
	 * 
	 * @return double[]
	 */
	public double[] asArray(int inputLayerSize) {

		int pos = 0;
		int length;
		double[] entradaRNA = new double[inputLayerSize];

		length = 4; // primeiraLinguagem.getPeso().length;
		System.arraycopy(primeiraLinguagem.getPeso(), 0, entradaRNA, pos, length);

		length = 4; // segundaLinguagem.getPeso().length;
		pos += length;
		System.arraycopy(segundaLinguagem.getPeso(), 0, entradaRNA, pos, length);

		length = 3; // tempoAprendizadoAlgoritmos.getPeso().length;
		pos += length + 1;
		System.arraycopy(tempoAprendizadoAlgoritmos.getPeso(), 0, entradaRNA, pos, length);

		length = 3; // tempoExperienciaProgramacao.getPeso().length;
		pos += length;
		System.arraycopy(tempoExperienciaProgramacao.getPeso(), 0, entradaRNA, pos, length);

		length = 3; // tempoSemProgramar.getPeso().length;
		pos += length;
		System.arraycopy(tempoSemProgramar.getPeso(), 0, entradaRNA, pos, length);

		length = 2; // trabalhaComProgramacao.getPeso().length;
		pos += length + 1;
		System.arraycopy(trabalhaComProgramacao.getPeso(), 0, entradaRNA, pos, length);

		length = 2; // usaOrientacaoObjetos.getPeso().length;
		pos += length;
		System.arraycopy(usaOrientacaoObjetos.getPeso(), 0, entradaRNA, pos, length);

		length = 2; // usaProgramacaoWeb.getPeso().length;
		pos += length;
		System.arraycopy(usaProgramacaoWeb.getPeso(), 0, entradaRNA, pos, length);

		length = 2; // dificuldadeConcentracao.getPeso().length;
		pos += length;
		System.arraycopy(dificuldadeConcentracao.getPeso(), 0, entradaRNA, pos, length);

		// for (int i = 0; i < entradaRNA.length; i++) {
		// System.out.println(entradaRNA[i]);
		// }
		// System.out.println("OK");

		return entradaRNA;
	}

	public Linguagem getPrimeiraLinguagem() {
		return primeiraLinguagem;
	}

	public void setPrimeiraLinguagem(Linguagem primeiraLinguagem) {
		this.primeiraLinguagem = primeiraLinguagem;
	}

	public Linguagem getSegundaLinguagem() {
		return segundaLinguagem;
	}

	public void setSegundaLinguagem(Linguagem segundaLinguagem) {
		this.segundaLinguagem = segundaLinguagem;
	}

	public TempoAno getTempoAprendizadoAlgoritmos() {
		return tempoAprendizadoAlgoritmos;
	}

	public void setTempoAprendizadoAlgoritmos(TempoAno tempoAprendizadoAlgoritmos) {
		this.tempoAprendizadoAlgoritmos = tempoAprendizadoAlgoritmos;
	}

	public TempoAno getTempoExperienciaProgramacao() {
		return tempoExperienciaProgramacao;
	}

	public void setTempoExperienciaProgramacao(TempoAno tempoExperienciaProgramacao) {
		this.tempoExperienciaProgramacao = tempoExperienciaProgramacao;
	}

	public TempoAno getTempoSemProgramar() {
		return tempoSemProgramar;
	}

	public void setTempoSemProgramar(TempoAno tempoSemProgramar) {
		this.tempoSemProgramar = tempoSemProgramar;
	}

	public SimNao getTrabalhaComProgramacao() {
		return trabalhaComProgramacao;
	}

	public void setTrabalhaComProgramacao(SimNao trabalhaComProgramacao) {
		this.trabalhaComProgramacao = trabalhaComProgramacao;
	}

	public SimNao getUsaOrientacaoObjetos() {
		return usaOrientacaoObjetos;
	}

	public void setUsaOrientacaoObjetos(SimNao usaOrientacaoObjetos) {
		this.usaOrientacaoObjetos = usaOrientacaoObjetos;
	}

	public SimNao getUsaProgramacaoWeb() {
		return usaProgramacaoWeb;
	}

	public void setUsaProgramacaoWeb(SimNao usaProgramacaoWeb) {
		this.usaProgramacaoWeb = usaProgramacaoWeb;
	}

	public SimNao getDificuldadeConcentracao() {
		return dificuldadeConcentracao;
	}

	public void setDificuldadeConcentracao(SimNao dificuldadeConcentracao) {
		this.dificuldadeConcentracao = dificuldadeConcentracao;
	}

}
