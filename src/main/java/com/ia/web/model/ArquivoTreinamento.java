package com.ia.web.model;

import java.math.BigInteger;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

import com.ia.web.model.resposta.Dificuldade;
import com.ia.web.model.resposta.Linguagem;
import com.ia.web.model.resposta.SimNao;
import com.ia.web.model.resposta.TempoAno;

public class ArquivoTreinamento {

	private Parametro parametro;

	private List<Opcoes> opcoes;
	
	

	public ArquivoTreinamento(Parametro parametro, List<Opcoes> opcoes) {
		super();
		this.parametro = parametro;
		this.opcoes = opcoes;
	}

	public ArquivoTreinamento() {
		this.parametro = new Parametro(1, "10,5,10", 0.300, 0.005, new BigInteger("500000"), false, 25, 25);
		this.opcoes = new LinkedList<>();
		
		opcoes.add(new Opcoes(Linguagem.JAVASCRIPT, Linguagem.JAVA, TempoAno.UM_ANO_MENOS, TempoAno.DOIS_ANOS, TempoAno.TRES_ANOS, SimNao.SIM, SimNao.NAO, SimNao.SIM, SimNao.NAO, Dificuldade.FACIL));
		opcoes.add(new Opcoes(Linguagem.C, Linguagem.CPP, TempoAno.QUATRO_ANOS, TempoAno.CINCO_ANOS_MAIS, TempoAno.UM_ANO_MENOS, SimNao.SIM, SimNao.NAO, SimNao.SIM, SimNao.NAO, Dificuldade.MODERADO));
		opcoes.add(new Opcoes(Linguagem.CSHARP, Linguagem.CSS, TempoAno.UM_ANO_MENOS, TempoAno.DOIS_ANOS, TempoAno.TRES_ANOS, SimNao.NAO, SimNao.NAO, SimNao.SIM, SimNao.SIM, Dificuldade.DIFICIL));
		opcoes.add(new Opcoes(Linguagem.PHP, Linguagem.PYTHON, TempoAno.QUATRO_ANOS, TempoAno.CINCO_ANOS_MAIS, TempoAno.UM_ANO_MENOS, SimNao.NAO, SimNao.SIM, SimNao.SIM, SimNao.NAO, Dificuldade.FACIL));
		opcoes.add(new Opcoes(Linguagem.RUBY, Linguagem.SHELL, TempoAno.CINCO_ANOS_MAIS, TempoAno.DOIS_ANOS, TempoAno.QUATRO_ANOS, SimNao.NAO, SimNao.SIM, SimNao.NAO, SimNao.SIM, Dificuldade.MODERADO));
	}
	
	public Parametro getParametro() {
		return parametro;
	}

	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}

	public List<Opcoes> getOpcoes() {
		return opcoes;
	}

	public void setOpcoes(List<Opcoes> opcoes) {
		this.opcoes = opcoes;
	}

	public class Opcoes {
		private Linguagem primeiraLinguagem;
		private Linguagem segundaLinguagem;
		private TempoAno tempoAprendizadoAlgoritmos;
		private TempoAno tempoExperienciaProgramacao;
		private TempoAno tempoSemProgramar;
		private SimNao trabalhaComProgramacao;
		private SimNao usaOrientacaoObjetos;
		private SimNao usaProgramacaoWeb;
		private SimNao dificuldadeConcentracao;
		private Dificuldade resultado;

		public Opcoes(Linguagem primeiraLinguagem, Linguagem segundaLinguagem, TempoAno tempoAprendizadoAlgoritmos,
				TempoAno tempoExperienciaProgramacao, TempoAno tempoSemProgramar, SimNao trabalhaComProgramacao,
				SimNao usaOrientacaoObjetos, SimNao usaProgramacaoWeb, SimNao dificuldadeConcentracao, Dificuldade resultado) {
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
			this.resultado = resultado;
		}

		public Dificuldade getResultado() {
			return resultado;
		}

		public void setResultado(Dificuldade resultado) {
			this.resultado = resultado;
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
}
