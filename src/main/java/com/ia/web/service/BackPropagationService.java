package com.ia.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ia.web.model.BackPropagation;
import com.ia.web.model.Parametro;
import com.ia.web.model.resposta.Dificuldade;

import ADReNA_API.Data.DataSet;
import ADReNA_API.NeuralNetwork.Backpropagation;

@Service
public class BackPropagationService {

	@Autowired
	private com.ia.web.model.BackPropagation backPropagation;

	// Configurar a RNA
	private int[] camada;

	// qtd de camadas de entrada, saida e as camadas intemediarias (vetor).
	private Backpropagation rede;

	/**
	 * Fornece parâmetros default para a exibição da tela na primeira vez.
	 */
	public BackPropagationService() {
		// System.out.println("chamouu agora");

		this.camada = new int[3];
		camada[0] = 10;
		camada[1] = 10;
		camada[2] = 5;

		rede = new Backpropagation(4, 2, camada);

		// seta a taxa de erro.
		rede.SetErrorRate(0.01);

		// taxa de aprendizado (Opcional)
		// rede.SetLearningRate(0);

	}

	public void treinar() throws Exception {

		Parametro param = backPropagation.getParametro();

		try {

			// Carrega os dados para treinamento.
			DataSet treino = backPropagation.getDadosTreino();

			// Treina a rede com o conjunto de treinameto.
			rede.Learn(treino);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Erro ao treinar rede!");
		}

	}

	/**
	 * Faz o reconhecimento (espera o meu padrao a ser reconhecido na entrada
	 * retorna o valor reconhecido
	 * 
	 * @throws Exception
	 */
	public Dificuldade reconhecer(BackPropagation backPropagation) throws Exception {

		double[] saida;
		try {
			saida = rede.Recognize(backPropagation.getDadosReconhecer());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Erro ao reconhecer conjunto de dados!");
		}

		System.out.println("Saída 0: " + Math.round(saida[0]));
		System.out.println("Saída 1: " + Math.round(saida[1]));

		saida = arredondarSaida(saida);

		return identificarSaida(saida);
	}

	/**
	 * Recebe a saída binária da RNA e converte para o objeto correspondente.
	 * 
	 * @param saida
	 * @return Dificuldade
	 */
	private Dificuldade identificarSaida(double[] saida) {
		int igual = 0;
		next: for (Dificuldade d : Dificuldade.values()) {
			for (int i = 0; i < d.getPeso().length; i++) {
				igual = 0;
				if (d.getPeso()[i] == saida[i]) {
					igual++;
				} else
					continue next;
				if (i == igual) {
					return d;
				}
			}
		}
		return null;
	}

	private double[] arredondarSaida(double[] saida) {

		for (int i = 0; i < saida.length; i++) {
			saida[i] = Math.round(saida[i]);
		}
		return saida;
	}

	/**
	 * Configura os parâmetros que serão usados.
	 */
	private void configurarParametros() {
		Parametro param = backPropagation.getParametro();

		int inputLayerSize = param.getInputLayerSize();
		int outputLayerSize = param.getOuputLayerSize();

		this.camada = param.getNeuroniosPorCamadaVetor();
		rede = new Backpropagation(inputLayerSize, outputLayerSize, camada);

		// seta a taxa de erro.
		rede.SetErrorRate(param.getErroAceitavel());

		// taxa de aprendizado (Opcional)
		rede.SetLearningRate(param.getTaxaAprendizagem());
	}

	public com.ia.web.model.BackPropagation getBackPropagation() {
		return backPropagation;
	}

	public void setBackPropagation(com.ia.web.model.BackPropagation backPropagation) {
		this.backPropagation = backPropagation;
		configurarParametros();
	}
}
