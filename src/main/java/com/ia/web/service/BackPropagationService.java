package com.ia.web.service;

import org.springframework.stereotype.Service;

import com.ia.web.model.Parametro;

import ADReNA_API.Data.DataSet;
import ADReNA_API.Data.DataSetObject;
import ADReNA_API.NeuralNetwork.Backpropagation;

@Service
public class BackPropagationService {

	// Configurar a RNA
	private int[] camada;

	// qtd de camadas de entrada, saida e as camadas intemediarias (vetor).
	private Backpropagation rede;

	public BackPropagationService() {
		this.camada = new int[3];
		camada[0] = 10;
		camada[1] = 10;
		camada[2] = 5;

		rede = new Backpropagation(4, 2, camada);

		// seta a taxa de erro.
		rede.SetErrorRate(0.01);

		// taxa de aprendizado (Opcional)
		rede.SetLearningRate(0);

	}

	public BackPropagationService(Parametro parametro) {
		this.camada = new int[parametro.getQtdCamadasIntermediarias()];
//		this.rede = parametro.getNeuroniosPorCamada();
	}

	public void treinar() throws Exception {

		// Criar o conjunto de treinamento
		// tem q ter o mesmo tamanho da rede (qtd de entrada e saida camada)
		DataSet treino = new DataSet(4, 2);
		try {
			// dois vetores com as entradas e saidas esperadas.
			treino.Add(new DataSetObject(new double[] { 0, 0, 0, 0 }, new double[] { 0, 0 }));
			treino.Add(new DataSetObject(new double[] { 0, 0, 0, 1 }, new double[] { 0, 0 }));

			treino.Add(new DataSetObject(new double[] { 0, 0, 1, 0 }, new double[] { 0, 0 }));

			treino.Add(new DataSetObject(new double[] { 0, 0, 1, 1 }, new double[] { 0, 1 }));
			treino.Add(new DataSetObject(new double[] { 0, 1, 0, 0 }, new double[] { 0, 0 }));
			treino.Add(new DataSetObject(new double[] { 0, 1, 0, 1 }, new double[] { 0, 1 }));
			treino.Add(new DataSetObject(new double[] { 0, 1, 1, 0 }, new double[] { 0, 1 }));
			treino.Add(new DataSetObject(new double[] { 0, 1, 1, 1 }, new double[] { 1, 0 }));
			treino.Add(new DataSetObject(new double[] { 1, 0, 0, 0 }, new double[] { 0, 1 }));
			treino.Add(new DataSetObject(new double[] { 1, 0, 0, 1 }, new double[] { 1, 0 }));
			treino.Add(new DataSetObject(new double[] { 1, 0, 1, 0 }, new double[] { 1, 0 }));
		} catch (Exception e) {
			throw new Exception("Erro ao criar conjunto de treinamento!");
		}

		// Treina a rede com o conjunto de treinameto.
		try {
			rede.Learn(treino);
		} catch (Exception e) {
			throw new Exception("Erro ao treinar rede!");
		}

		// System.out.println(rede.GetMaxIterationNumber());

	}

	// faz o reconhecimento (espera o meu padrao a ser reconhecido na
	// entrada)
	// retorna o valor reconhecido
	public void reconhecer() throws Exception {

		double[] saida;
		try {
			saida = rede.Recognize(new double[] { 1, 0, 1, 1 });
		} catch (Exception e) {
			throw new Exception("Erro ao reconhecer conjunto de dados!");
		}

		System.out.println("Saída 0: " + Math.round(saida[0]));
		System.out.println("Saída 1: " + Math.round(saida[1]));

	}
}
