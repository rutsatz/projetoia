package com.ia.web.model;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.ia.web.model.ArquivoTreinamento.Opcoes;
import com.ia.web.model.resposta.Dificuldade;
import com.ia.web.model.resposta.Linguagem;
import com.ia.web.model.resposta.SimNao;
import com.ia.web.model.resposta.TempoAno;

import ADReNA_API.Data.DataSet;
import ADReNA_API.Data.DataSetObject;

public class Treino {

	MultipartFile file;
	int inputLayerSize;
	int outputLayerSize;
	ArquivoTreinamento arq;

	public Treino(MultipartFile file, int inputLayerSize, int outputLayerSize) throws IOException {
		this.file = file;
		this.inputLayerSize = inputLayerSize;
		this.outputLayerSize = outputLayerSize;

		String content = new String(file.getBytes());

		Gson gson = new Gson();

		arq = gson.fromJson(content, ArquivoTreinamento.class);
	}

	public Parametro getParametro() {
		return arq.getParametro();
	}

	public DataSet getTreinoArquivo() throws Exception {

		DataSet treino = new DataSet(inputLayerSize, outputLayerSize);
		try {

			for (Opcoes opcao : arq.getOpcoes()) {

				TreinoEntrada te = new TreinoEntrada(opcao.getPrimeiraLinguagem(), opcao.getSegundaLinguagem(),
						opcao.getTempoAprendizadoAlgoritmos(), opcao.getTempoExperienciaProgramacao(),
						opcao.getTempoSemProgramar(), opcao.getTrabalhaComProgramacao(),
						opcao.getUsaOrientacaoObjetos(), opcao.getUsaProgramacaoWeb(),
						opcao.getDificuldadeConcentracao());
				TreinoSaida ts = new TreinoSaida(opcao.getResultado());
				treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			}
		} catch (Exception e) {
			throw new Exception("Erro ao criar conjunto de treinamento usando arquivo!");

		}
		return treino;
	}

	/**
	 * Retorna os dados padrões resultantes da pesquisa.
	 * 
	 * @return DataSet
	 * @throws Exception
	 */
	public static DataSet getTreinoDefault(int inputLayerSize, int outputLayerSize) throws Exception {

		DataSet treino = new DataSet(inputLayerSize, outputLayerSize);
		try {
			// dois vetores com as entradas e saidas esperadas.

			// 1
			TreinoEntrada te = new TreinoEntrada(Linguagem.CPP, Linguagem.JAVA, TempoAno.CINCO_ANOS_MAIS,
					TempoAno.CINCO_ANOS_MAIS, TempoAno.UM_ANO_MENOS, SimNao.NAO, SimNao.SIM, SimNao.SIM, SimNao.NAO);
			TreinoSaida ts = new TreinoSaida(Dificuldade.MODERADO);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 2
			te = new TreinoEntrada(Linguagem.C, Linguagem.JAVA, TempoAno.TRES_ANOS, TempoAno.DOIS_ANOS,
					TempoAno.UM_ANO_MENOS, SimNao.NAO, SimNao.NAO, SimNao.NAO, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.MODERADO);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 3
			te = new TreinoEntrada(Linguagem.CSHARP, Linguagem.PHP, TempoAno.UM_ANO_MENOS, TempoAno.QUATRO_ANOS,
					TempoAno.UM_ANO_MENOS, SimNao.NAO, SimNao.SIM, SimNao.SIM, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.FACIL);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 4
			te = new TreinoEntrada(Linguagem.C, Linguagem.JAVA, TempoAno.QUATRO_ANOS, TempoAno.QUATRO_ANOS,
					TempoAno.CINCO_ANOS_MAIS, SimNao.SIM, SimNao.SIM, SimNao.SIM, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.DIFICIL);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 5
			te = new TreinoEntrada(Linguagem.CPP, Linguagem.JAVA, TempoAno.DOIS_ANOS, TempoAno.UM_ANO_MENOS,
					TempoAno.UM_ANO_MENOS, SimNao.NAO, SimNao.SIM, SimNao.NAO, SimNao.SIM);
			ts = new TreinoSaida(Dificuldade.DIFICIL);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 6
			te = new TreinoEntrada(Linguagem.JAVA, Linguagem.CPP, TempoAno.QUATRO_ANOS, TempoAno.QUATRO_ANOS,
					TempoAno.UM_ANO_MENOS, SimNao.SIM, SimNao.SIM, SimNao.SIM, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.MODERADO);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 7
			te = new TreinoEntrada(Linguagem.CPP, Linguagem.JAVA, TempoAno.CINCO_ANOS_MAIS, TempoAno.TRES_ANOS,
					TempoAno.UM_ANO_MENOS, SimNao.SIM, SimNao.SIM, SimNao.NAO, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.DIFICIL);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 8
			te = new TreinoEntrada(Linguagem.JAVA, Linguagem.PHP, TempoAno.UM_ANO_MENOS, TempoAno.UM_ANO_MENOS,
					TempoAno.UM_ANO_MENOS, SimNao.NAO, SimNao.SIM, SimNao.NAO, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.MODERADO);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 9
			te = new TreinoEntrada(Linguagem.CSHARP, Linguagem.JAVA, TempoAno.TRES_ANOS, TempoAno.TRES_ANOS,
					TempoAno.UM_ANO_MENOS, SimNao.NAO, SimNao.SIM, SimNao.NAO, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.MODERADO);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 10
			te = new TreinoEntrada(Linguagem.JAVA, Linguagem.CPP, TempoAno.TRES_ANOS, TempoAno.DOIS_ANOS,
					TempoAno.TRES_ANOS, SimNao.NAO, SimNao.SIM, SimNao.SIM, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.FACIL);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 11
			te = new TreinoEntrada(Linguagem.CSHARP, Linguagem.JAVA, TempoAno.UM_ANO_MENOS, TempoAno.UM_ANO_MENOS,
					TempoAno.UM_ANO_MENOS, SimNao.SIM, SimNao.SIM, SimNao.NAO, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.MODERADO);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 12
			te = new TreinoEntrada(Linguagem.JAVA, Linguagem.CPP, TempoAno.QUATRO_ANOS, TempoAno.DOIS_ANOS,
					TempoAno.UM_ANO_MENOS, SimNao.SIM, SimNao.SIM, SimNao.SIM, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.DIFICIL);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 13
			te = new TreinoEntrada(Linguagem.C, Linguagem.JAVA, TempoAno.CINCO_ANOS_MAIS, TempoAno.TRES_ANOS,
					TempoAno.UM_ANO_MENOS, SimNao.SIM, SimNao.NAO, SimNao.SIM, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.FACIL);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 14
			te = new TreinoEntrada(Linguagem.C, Linguagem.JAVA, TempoAno.QUATRO_ANOS, TempoAno.QUATRO_ANOS,
					TempoAno.UM_ANO_MENOS, SimNao.NAO, SimNao.SIM, SimNao.SIM, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.FACIL);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 15
			te = new TreinoEntrada(Linguagem.C, Linguagem.JAVASCRIPT, TempoAno.TRES_ANOS, TempoAno.DOIS_ANOS,
					TempoAno.UM_ANO_MENOS, SimNao.NAO, SimNao.SIM, SimNao.NAO, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.MODERADO);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 16
			te = new TreinoEntrada(Linguagem.JAVASCRIPT, Linguagem.CSS, TempoAno.UM_ANO_MENOS, TempoAno.UM_ANO_MENOS,
					TempoAno.UM_ANO_MENOS, SimNao.NAO, SimNao.NAO, SimNao.SIM, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.FACIL);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 17
			te = new TreinoEntrada(Linguagem.CSS, Linguagem.PHP, TempoAno.DOIS_ANOS, TempoAno.CINCO_ANOS_MAIS,
					TempoAno.UM_ANO_MENOS, SimNao.SIM, SimNao.SIM, SimNao.SIM, SimNao.SIM);
			ts = new TreinoSaida(Dificuldade.MODERADO);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 18
			te = new TreinoEntrada(Linguagem.CSS, Linguagem.JAVA, TempoAno.CINCO_ANOS_MAIS, TempoAno.QUATRO_ANOS,
					TempoAno.UM_ANO_MENOS, SimNao.NAO, SimNao.SIM, SimNao.SIM, SimNao.SIM);
			ts = new TreinoSaida(Dificuldade.MODERADO);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 19
			te = new TreinoEntrada(Linguagem.C, Linguagem.CPP, TempoAno.CINCO_ANOS_MAIS, TempoAno.CINCO_ANOS_MAIS,
					TempoAno.CINCO_ANOS_MAIS, SimNao.NAO, SimNao.SIM, SimNao.SIM, SimNao.SIM);
			ts = new TreinoSaida(Dificuldade.MODERADO);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 20
			te = new TreinoEntrada(Linguagem.JAVASCRIPT, Linguagem.JAVA, TempoAno.UM_ANO_MENOS, TempoAno.TRES_ANOS,
					TempoAno.UM_ANO_MENOS, SimNao.SIM, SimNao.SIM, SimNao.SIM, SimNao.SIM);
			ts = new TreinoSaida(Dificuldade.MODERADO);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 21
			te = new TreinoEntrada(Linguagem.C, Linguagem.JAVA, TempoAno.CINCO_ANOS_MAIS, TempoAno.CINCO_ANOS_MAIS,
					TempoAno.CINCO_ANOS_MAIS, SimNao.SIM, SimNao.SIM, SimNao.SIM, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.MODERADO);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 22
			te = new TreinoEntrada(Linguagem.C, Linguagem.CPP, TempoAno.CINCO_ANOS_MAIS, TempoAno.TRES_ANOS,
					TempoAno.UM_ANO_MENOS, SimNao.SIM, SimNao.NAO, SimNao.NAO, SimNao.SIM);
			ts = new TreinoSaida(Dificuldade.MODERADO);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 23
			te = new TreinoEntrada(Linguagem.C, Linguagem.JAVA, TempoAno.DOIS_ANOS, TempoAno.DOIS_ANOS,
					TempoAno.UM_ANO_MENOS, SimNao.NAO, SimNao.NAO, SimNao.SIM, SimNao.SIM);
			ts = new TreinoSaida(Dificuldade.MODERADO);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 24
			te = new TreinoEntrada(Linguagem.C, Linguagem.CPP, TempoAno.CINCO_ANOS_MAIS, TempoAno.CINCO_ANOS_MAIS,
					TempoAno.UM_ANO_MENOS, SimNao.NAO, SimNao.SIM, SimNao.NAO, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.MODERADO);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 25
			te = new TreinoEntrada(Linguagem.C, Linguagem.CPP, TempoAno.CINCO_ANOS_MAIS, TempoAno.CINCO_ANOS_MAIS,
					TempoAno.UM_ANO_MENOS, SimNao.SIM, SimNao.SIM, SimNao.NAO, SimNao.SIM);
			ts = new TreinoSaida(Dificuldade.MODERADO);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 26
			te = new TreinoEntrada(Linguagem.CPP, Linguagem.C, TempoAno.DOIS_ANOS, TempoAno.DOIS_ANOS,
					TempoAno.UM_ANO_MENOS, SimNao.NAO, SimNao.SIM, SimNao.SIM, SimNao.SIM);
			ts = new TreinoSaida(Dificuldade.FACIL);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 27
			te = new TreinoEntrada(Linguagem.C, Linguagem.JAVA, TempoAno.UM_ANO_MENOS, TempoAno.UM_ANO_MENOS,
					TempoAno.UM_ANO_MENOS, SimNao.NAO, SimNao.SIM, SimNao.NAO, SimNao.SIM);
			ts = new TreinoSaida(Dificuldade.MODERADO);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 28
			te = new TreinoEntrada(Linguagem.CPP, Linguagem.C, TempoAno.DOIS_ANOS, TempoAno.DOIS_ANOS,
					TempoAno.UM_ANO_MENOS, SimNao.NAO, SimNao.SIM, SimNao.NAO, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.FACIL);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 29
			te = new TreinoEntrada(Linguagem.C, Linguagem.JAVA, TempoAno.QUATRO_ANOS, TempoAno.QUATRO_ANOS,
					TempoAno.UM_ANO_MENOS, SimNao.NAO, SimNao.NAO, SimNao.NAO, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.FACIL);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 30
			te = new TreinoEntrada(Linguagem.CPP, Linguagem.CSHARP, TempoAno.DOIS_ANOS, TempoAno.TRES_ANOS,
					TempoAno.UM_ANO_MENOS, SimNao.NAO, SimNao.SIM, SimNao.SIM, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.MODERADO);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 31
			te = new TreinoEntrada(Linguagem.PHP, Linguagem.JAVA, TempoAno.TRES_ANOS, TempoAno.TRES_ANOS,
					TempoAno.UM_ANO_MENOS, SimNao.SIM, SimNao.SIM, SimNao.SIM, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.MODERADO);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 32
			te = new TreinoEntrada(Linguagem.C, Linguagem.CPP, TempoAno.TRES_ANOS, TempoAno.TRES_ANOS,
					TempoAno.UM_ANO_MENOS, SimNao.NAO, SimNao.NAO, SimNao.NAO, SimNao.SIM);
			ts = new TreinoSaida(Dificuldade.MODERADO);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 33
			te = new TreinoEntrada(Linguagem.JAVA, Linguagem.PYTHON, TempoAno.CINCO_ANOS_MAIS, TempoAno.TRES_ANOS,
					TempoAno.UM_ANO_MENOS, SimNao.NAO, SimNao.SIM, SimNao.NAO, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.FACIL);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 34
			te = new TreinoEntrada(Linguagem.PHP, Linguagem.JAVASCRIPT, TempoAno.TRES_ANOS, TempoAno.DOIS_ANOS,
					TempoAno.UM_ANO_MENOS, SimNao.SIM, SimNao.SIM, SimNao.SIM, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.MODERADO);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 35
			te = new TreinoEntrada(Linguagem.C, Linguagem.JAVA, TempoAno.TRES_ANOS, TempoAno.DOIS_ANOS,
					TempoAno.UM_ANO_MENOS, SimNao.NAO, SimNao.SIM, SimNao.NAO, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.DIFICIL);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 36
			te = new TreinoEntrada(Linguagem.C, Linguagem.JAVA, TempoAno.UM_ANO_MENOS, TempoAno.UM_ANO_MENOS,
					TempoAno.UM_ANO_MENOS, SimNao.SIM, SimNao.SIM, SimNao.NAO, SimNao.SIM);
			ts = new TreinoSaida(Dificuldade.FACIL);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 37
			te = new TreinoEntrada(Linguagem.C, Linguagem.JAVA, TempoAno.DOIS_ANOS, TempoAno.TRES_ANOS,
					TempoAno.UM_ANO_MENOS, SimNao.NAO, SimNao.SIM, SimNao.SIM, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.MODERADO);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 38
			te = new TreinoEntrada(Linguagem.C, Linguagem.JAVA, TempoAno.DOIS_ANOS, TempoAno.DOIS_ANOS,
					TempoAno.UM_ANO_MENOS, SimNao.NAO, SimNao.SIM, SimNao.SIM, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.MODERADO);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 39
			te = new TreinoEntrada(Linguagem.C, Linguagem.JAVA, TempoAno.CINCO_ANOS_MAIS, TempoAno.CINCO_ANOS_MAIS,
					TempoAno.UM_ANO_MENOS, SimNao.NAO, SimNao.SIM, SimNao.NAO, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.MODERADO);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 40
			te = new TreinoEntrada(Linguagem.C, Linguagem.JAVA, TempoAno.CINCO_ANOS_MAIS, TempoAno.TRES_ANOS,
					TempoAno.UM_ANO_MENOS, SimNao.NAO, SimNao.NAO, SimNao.NAO, SimNao.SIM);
			ts = new TreinoSaida(Dificuldade.DIFICIL);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 41
			te = new TreinoEntrada(Linguagem.PHP, Linguagem.JAVA, TempoAno.CINCO_ANOS_MAIS, TempoAno.UM_ANO_MENOS,
					TempoAno.DOIS_ANOS, SimNao.NAO, SimNao.SIM, SimNao.SIM, SimNao.SIM);
			ts = new TreinoSaida(Dificuldade.MODERADO);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 42
			te = new TreinoEntrada(Linguagem.PHP, Linguagem.CSS, TempoAno.CINCO_ANOS_MAIS, TempoAno.QUATRO_ANOS,
					TempoAno.UM_ANO_MENOS, SimNao.SIM, SimNao.SIM, SimNao.SIM, SimNao.SIM);
			ts = new TreinoSaida(Dificuldade.FACIL);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 43
			te = new TreinoEntrada(Linguagem.PHP, Linguagem.JAVA, TempoAno.UM_ANO_MENOS, TempoAno.UM_ANO_MENOS,
					TempoAno.UM_ANO_MENOS, SimNao.SIM, SimNao.SIM, SimNao.SIM, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.MODERADO);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 44
			te = new TreinoEntrada(Linguagem.C, Linguagem.JAVA, TempoAno.DOIS_ANOS, TempoAno.UM_ANO_MENOS,
					TempoAno.UM_ANO_MENOS, SimNao.NAO, SimNao.SIM, SimNao.NAO, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.MODERADO);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 45
			te = new TreinoEntrada(Linguagem.C, Linguagem.CPP, TempoAno.QUATRO_ANOS, TempoAno.UM_ANO_MENOS,
					TempoAno.DOIS_ANOS, SimNao.NAO, SimNao.NAO, SimNao.NAO, SimNao.SIM);
			ts = new TreinoSaida(Dificuldade.MODERADO);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 46
			te = new TreinoEntrada(Linguagem.CPP, Linguagem.JAVA, TempoAno.QUATRO_ANOS, TempoAno.QUATRO_ANOS,
					TempoAno.UM_ANO_MENOS, SimNao.NAO, SimNao.SIM, SimNao.NAO, SimNao.SIM);
			ts = new TreinoSaida(Dificuldade.FACIL);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			// 47
			te = new TreinoEntrada(Linguagem.C, Linguagem.CPP, TempoAno.CINCO_ANOS_MAIS, TempoAno.TRES_ANOS,
					TempoAno.TRES_ANOS, SimNao.SIM, SimNao.SIM, SimNao.SIM, SimNao.SIM);
			ts = new TreinoSaida(Dificuldade.FACIL);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

		} catch (Exception e) {
			throw new Exception("Erro ao criar conjunto de treinamento!");
		}
		return treino;
	}
}
