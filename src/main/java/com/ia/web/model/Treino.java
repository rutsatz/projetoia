package com.ia.web.model;

import com.ia.web.model.resposta.Dificuldade;
import com.ia.web.model.resposta.Linguagem;
import com.ia.web.model.resposta.SimNao;
import com.ia.web.model.resposta.TempoAno;

import ADReNA_API.Data.DataSet;
import ADReNA_API.Data.DataSetObject;

public class Treino {

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

			TreinoEntrada te = new TreinoEntrada(Linguagem.PHP, Linguagem.PHP, TempoAno.QUATRO_ANOS,
					TempoAno.CINCO_ANOS_MAIS, TempoAno.QUATRO_ANOS, SimNao.NAO, SimNao.NAO, SimNao.SIM, SimNao.NAO);
			TreinoSaida ts = new TreinoSaida(Dificuldade.FACIL);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			te = new TreinoEntrada(Linguagem.CSHARP, Linguagem.PYTHON, TempoAno.TRES_ANOS, TempoAno.DOIS_ANOS,
					TempoAno.TRES_ANOS, SimNao.NAO, SimNao.NAO, SimNao.NAO, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.FACIL);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			te = new TreinoEntrada(Linguagem.JAVASCRIPT, Linguagem.C, TempoAno.TRES_ANOS, TempoAno.UM_ANO_MENOS,
					TempoAno.TRES_ANOS, SimNao.SIM, SimNao.SIM, SimNao.SIM, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.FACIL);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			te = new TreinoEntrada(Linguagem.JAVASCRIPT, Linguagem.C, TempoAno.TRES_ANOS, TempoAno.UM_ANO_MENOS,
					TempoAno.TRES_ANOS, SimNao.SIM, SimNao.SIM, SimNao.SIM, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.MODERADO);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			te = new TreinoEntrada(Linguagem.CPP, Linguagem.C, TempoAno.TRES_ANOS, TempoAno.UM_ANO_MENOS,
					TempoAno.TRES_ANOS, SimNao.SIM, SimNao.SIM, SimNao.SIM, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.MODERADO);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			te = new TreinoEntrada(Linguagem.PYTHON, Linguagem.C, TempoAno.TRES_ANOS, TempoAno.UM_ANO_MENOS,
					TempoAno.TRES_ANOS, SimNao.SIM, SimNao.SIM, SimNao.SIM, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.DIFICIL);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

			te = new TreinoEntrada(Linguagem.RUBY, Linguagem.C, TempoAno.TRES_ANOS, TempoAno.UM_ANO_MENOS,
					TempoAno.TRES_ANOS, SimNao.SIM, SimNao.SIM, SimNao.SIM, SimNao.NAO);
			ts = new TreinoSaida(Dificuldade.DIFICIL);
			treino.Add(new DataSetObject(te.asArray(inputLayerSize), ts.asArray(outputLayerSize)));

		} catch (Exception e) {
			throw new Exception("Erro ao criar conjunto de treinamento!");
		}
		return treino;
	}
}
