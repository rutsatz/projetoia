package com.ia.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ia.web.model.Estatistica;
import com.ia.web.model.Resposta;

public interface Respostas extends JpaRepository<Resposta, Long>{

	@Query("select new com.ia.web.model.Estatistica(r.backPropagation.primeiraLinguagem"
			+ ", r.backPropagation.segundaLinguagem"
			+ ", r.resultado"
			+ ", count(*) as quantidade )"
			+ " from Resposta r"
			+ " group by r.backPropagation.primeiraLinguagem"
			+ ", r.backPropagation.segundaLinguagem"
			+ ", r.resultado"
			+ " order by count(*) desc"
			+ " , r.backPropagation.primeiraLinguagem"
			+ " , r.backPropagation.segundaLinguagem"
			+ " , r.resultado")
	public List<Estatistica> getEstatisticas();
	
}
