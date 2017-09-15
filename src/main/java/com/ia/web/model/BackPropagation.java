package com.ia.web.model;

import java.math.BigInteger;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ia.web.model.resposta.Dificuldade;
import com.ia.web.model.resposta.Linguagem;
import com.ia.web.model.resposta.SimNao;
import com.ia.web.model.resposta.TempoAno;

import ADReNA_API.Data.DataSet;

@Embeddable
@Service
public class BackPropagation {

	@Valid
	private Parametro parametro;

	// Qual foi a primeira linguagem de programação que você aprendeu?
	@Enumerated(EnumType.STRING)
	private Linguagem primeiraLinguagem;

	// Qual linguagem você deseja aprender?
	@Enumerated(EnumType.STRING)
	private Linguagem segundaLinguagem;

	// Quanto tempo (anos) faz que você aprendeu algoritmos?
	@Enumerated(EnumType.STRING)
	private TempoAno tempoAprendizadoAlgoritmos;

	// Quanto tempo (anos) você tem de experiência em programação?
	@Enumerated(EnumType.STRING)
	private TempoAno tempoExperienciaProgramacao;

	// Quanto tempo (anos) você não programa?
	@Enumerated(EnumType.STRING)
	private TempoAno tempoSemProgramar;

	// Atualmente você trabalha com programação?
	@Enumerated(EnumType.STRING)
	private SimNao trabalhaComProgramacao;

	// Você utiliza programação orientada a objetos?
	@Enumerated(EnumType.STRING)
	private SimNao usaOrientacaoObjetos;

	// Você utiliza programação web?
	@Enumerated(EnumType.STRING)
	private SimNao usaProgramacaoWeb;

	// Você tem dificuldade de concentração?
	@Enumerated(EnumType.STRING)
	private SimNao dificuldadeConcentracao;

	@Transient
	private MultipartFile arquivoTreinamento;

	// Indica se a RNA já foi treinada.
	private boolean rnaTreinada;

	/**
	 * Construtor que inicializa com valores padrões para a página de boas vindas.
	 */
	public BackPropagation() {

		// Define parâmetros default quando nenhum parâmetro
		// for definido.
		this.parametro = new Parametro(1, // Qtd camadas intermediárias.
				"10,5,10", // Qtd neurônios por camada.
				0.3, // Taxa de aprendizagem.
				0.005, // Erro aceitável.
				new BigInteger("500000"), // Número de iterações.
				false, // usaArquivo
				calcularCamadaEntrada(), // inputLayerSize @@ Preencher
				calcularCamadaSaida() // ouputLayerSize @@ Preencher
		);

		// Define opções default da view.
		this.primeiraLinguagem = Linguagem.JAVA;
		this.segundaLinguagem = Linguagem.PHP;
		this.tempoAprendizadoAlgoritmos = TempoAno.UM_ANO_MENOS;
		this.tempoExperienciaProgramacao = TempoAno.UM_ANO_MENOS;
		this.tempoSemProgramar = TempoAno.UM_ANO_MENOS;
		this.trabalhaComProgramacao = SimNao.SIM;
		this.usaOrientacaoObjetos = SimNao.SIM;
		this.usaProgramacaoWeb = SimNao.SIM;
		this.dificuldadeConcentracao = SimNao.SIM;
		this.rnaTreinada = false;

	}

	/**
	 * Calcular manualmente esse valores baseado nas linguagens de entrada. Isso
	 * acontece pq enums não funcionam com polimorfismo nem generics.
	 * 
	 * @return
	 */
	public int calcularCamadaEntrada() {

		int length = 0;

		length += Linguagem.JAVA.getPeso().length; // primeiraLinguagem

		length += Linguagem.JAVA.getPeso().length; // segundaLinguagem

		length += TempoAno.UM_ANO_MENOS.getPeso().length; // tempoAprendizadoAlgoritmos

		length += TempoAno.UM_ANO_MENOS.getPeso().length; // tempoExperienciaProgramacao

		length += TempoAno.UM_ANO_MENOS.getPeso().length; // tempoSemProgramar

		length += SimNao.SIM.getPeso().length; // trabalhaComProgramacao

		length += SimNao.SIM.getPeso().length; // usaOrientacaoObjetos

		length += SimNao.SIM.getPeso().length; // usaProgramacaoWeb

		length += SimNao.SIM.getPeso().length; // dificuldadeConcentracao

		return length;
	}

	/**
	 * Retona a quantidade de camadas de saída.
	 * 
	 * @return int
	 */
	private int calcularCamadaSaida() {

		int length = 0;

		length += Dificuldade.FACIL.getPeso().length;

		return length;
	}

	/**
	 * Criar o conjunto de treinamento tem q ter o mesmo tamanho da rede (qtd de
	 * entrada e saida camada)
	 * 
	 * @return DataSet
	 * @throws Exception
	 */
	public DataSet getDadosTreino() throws Exception {
		DataSet treino = null;

		if (parametro.getUsarArquivo()) {
			// Aqui tratar parse do arquivo.

		} else {
			treino = Treino.getTreinoDefault(parametro.getInputLayerSize(), parametro.getOuputLayerSize());
		}

		return treino;
	}

	/**
	 * Pega as opções que o usuário selecionou e converte para o formato aceito pela
	 * RNA.
	 * 
	 * @return double[]
	 */
	public double[] getDadosReconhecer() {

		int inputLayerSize = calcularCamadaEntrada();

		int pos = 0;
		int length;
		double[] inputRecognize = new double[inputLayerSize];

		length = 4; // primeiraLinguagem.getPeso().length;
		System.arraycopy(primeiraLinguagem.getPeso(), 0, inputRecognize, pos, length);

		length = 4; // segundaLinguagem.getPeso().length;
		pos += length;
		System.arraycopy(segundaLinguagem.getPeso(), 0, inputRecognize, pos, length);

		length = 3; // tempoAprendizadoAlgoritmos.getPeso().length;
		pos += length + 1;
		System.arraycopy(tempoAprendizadoAlgoritmos.getPeso(), 0, inputRecognize, pos, length);

		length = 3; // tempoExperienciaProgramacao.getPeso().length;
		pos += length;
		System.arraycopy(tempoExperienciaProgramacao.getPeso(), 0, inputRecognize, pos, length);

		length = 3; // tempoSemProgramar.getPeso().length;
		pos += length;
		System.arraycopy(tempoSemProgramar.getPeso(), 0, inputRecognize, pos, length);

		length = 2; // trabalhaComProgramacao.getPeso().length;
		pos += length + 1;
		System.arraycopy(trabalhaComProgramacao.getPeso(), 0, inputRecognize, pos, length);

		length = 2; // usaOrientacaoObjetos.getPeso().length;
		pos += length;
		System.arraycopy(usaOrientacaoObjetos.getPeso(), 0, inputRecognize, pos, length);

		length = 2; // usaProgramacaoWeb.getPeso().length;
		pos += length;
		System.arraycopy(usaProgramacaoWeb.getPeso(), 0, inputRecognize, pos, length);

		length = 2; // dificuldadeConcentracao.getPeso().length;
		pos += length;
		System.arraycopy(dificuldadeConcentracao.getPeso(), 0, inputRecognize, pos, length);

		// for (int i = 0; i < entradaRNA.length; i++) {
		// System.out.println(entradaRNA[i]);
		// }
		// System.out.println("OK");

		return inputRecognize;

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

	public MultipartFile getArquivoTreinamento() {
		return arquivoTreinamento;
	}

	public void setArquivoTreinamento(MultipartFile arquivoTreinamento) {
		this.arquivoTreinamento = arquivoTreinamento;
	}

	public boolean isRnaTreinada() {
		return rnaTreinada;
	}

	public void setRnaTreinada(boolean rnaTreinada) {
		this.rnaTreinada = rnaTreinada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arquivoTreinamento == null) ? 0 : arquivoTreinamento.hashCode());
		result = prime * result + ((dificuldadeConcentracao == null) ? 0 : dificuldadeConcentracao.hashCode());
		result = prime * result + ((parametro == null) ? 0 : parametro.hashCode());
		result = prime * result + ((primeiraLinguagem == null) ? 0 : primeiraLinguagem.hashCode());
		result = prime * result + (rnaTreinada ? 1231 : 1237);
		result = prime * result + ((segundaLinguagem == null) ? 0 : segundaLinguagem.hashCode());
		result = prime * result + ((tempoAprendizadoAlgoritmos == null) ? 0 : tempoAprendizadoAlgoritmos.hashCode());
		result = prime * result + ((tempoExperienciaProgramacao == null) ? 0 : tempoExperienciaProgramacao.hashCode());
		result = prime * result + ((tempoSemProgramar == null) ? 0 : tempoSemProgramar.hashCode());
		result = prime * result + ((trabalhaComProgramacao == null) ? 0 : trabalhaComProgramacao.hashCode());
		result = prime * result + ((usaOrientacaoObjetos == null) ? 0 : usaOrientacaoObjetos.hashCode());
		result = prime * result + ((usaProgramacaoWeb == null) ? 0 : usaProgramacaoWeb.hashCode());
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
		BackPropagation other = (BackPropagation) obj;
		if (arquivoTreinamento == null) {
			if (other.arquivoTreinamento != null)
				return false;
		} else if (!arquivoTreinamento.equals(other.arquivoTreinamento))
			return false;
		if (dificuldadeConcentracao != other.dificuldadeConcentracao)
			return false;
		if (parametro == null) {
			if (other.parametro != null)
				return false;
		} else if (!parametro.equals(other.parametro))
			return false;
		if (primeiraLinguagem != other.primeiraLinguagem)
			return false;
		if (rnaTreinada != other.rnaTreinada)
			return false;
		if (segundaLinguagem != other.segundaLinguagem)
			return false;
		if (tempoAprendizadoAlgoritmos != other.tempoAprendizadoAlgoritmos)
			return false;
		if (tempoExperienciaProgramacao != other.tempoExperienciaProgramacao)
			return false;
		if (tempoSemProgramar != other.tempoSemProgramar)
			return false;
		if (trabalhaComProgramacao != other.trabalhaComProgramacao)
			return false;
		if (usaOrientacaoObjetos != other.usaOrientacaoObjetos)
			return false;
		if (usaProgramacaoWeb != other.usaProgramacaoWeb)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BackPropagation [parametro=");
		builder.append(parametro);
		builder.append(", primeiraLinguagem=");
		builder.append(primeiraLinguagem);
		builder.append(", segundaLinguagem=");
		builder.append(segundaLinguagem);
		builder.append(", tempoAprendizadoAlgoritmos=");
		builder.append(tempoAprendizadoAlgoritmos);
		builder.append(", tempoExperienciaProgramacao=");
		builder.append(tempoExperienciaProgramacao);
		builder.append(", tempoSemProgramar=");
		builder.append(tempoSemProgramar);
		builder.append(", trabalhaComProgramacao=");
		builder.append(trabalhaComProgramacao);
		builder.append(", usaOrientacaoObjetos=");
		builder.append(usaOrientacaoObjetos);
		builder.append(", usaProgramacaoWeb=");
		builder.append(usaProgramacaoWeb);
		builder.append(", dificuldadeConcentracao=");
		builder.append(dificuldadeConcentracao);
		builder.append(", arquivoTreinamento=");
		builder.append(arquivoTreinamento);
		builder.append(", rnaTreinada=");
		builder.append(rnaTreinada);
		builder.append("]");
		return builder.toString();
	}

}
