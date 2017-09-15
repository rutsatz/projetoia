package com.ia.web.model;

import com.ia.web.model.resposta.Dificuldade;

/**
 * Representa as saídas esperadas da minha RNA.
 * 
 * Param 1: Dificuldade de aprendizado.
 * 
 * @author Rafael
 *
 */
public class TreinoSaida {

	private Dificuldade dificuldade;

	public TreinoSaida(Dificuldade dificuldade) {
		super();
		this.dificuldade = dificuldade;
	}

	/**
	 * Retorna os dados no formato definido para a RNA.
	 * 
	 * @param outputLayerSize
	 * @return double[]
	 */
	public double[] asArray(int outputLayerSize) {
		int pos = 0;
		int length;
		double[] entradaRNA = new double[outputLayerSize];

		length = dificuldade.getPeso().length;
		System.arraycopy(dificuldade.getPeso(), 0, entradaRNA, pos, length);

		// for (int i = 0; i < entradaRNA.length; i++) {
		// System.out.println(entradaRNA[i]);
		// }
		// System.out.println("OK - Saída");

		return entradaRNA;
	}

	public Dificuldade getDificuldade() {
		return dificuldade;
	}

	public void setDificuldade(Dificuldade dificuldade) {
		this.dificuldade = dificuldade;
	}

}
