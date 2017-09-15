package com.ia.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ia.web.model.Estatistica;
import com.ia.web.model.Resposta;
import com.ia.web.repository.Respostas;

@Service
public class RespostaService {

	@Autowired
	private Respostas respostas;

	public void salvar(Resposta resposta) {
		respostas.save(resposta);
	}
	
	public List<Estatistica> estatisticas(){
		return respostas.getEstatisticas();
	}
	
	public long total() {
		return respostas.count();
	}
}
