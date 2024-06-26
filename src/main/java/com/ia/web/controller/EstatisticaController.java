package com.ia.web.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ia.web.model.Estatistica;
import com.ia.web.service.RespostaService;

@Controller
public class EstatisticaController {

	@Autowired
	private RespostaService respostaService;

	@RequestMapping("/estatistica")
	public ModelAndView estatistica(HttpServletRequest httpServletRequest) {

		ModelAndView mv = new ModelAndView("Estatistica");

		long totalRespostas = respostaService.total();

		List<Estatistica> estatisticas = respostaService.estatisticas();

		for (Estatistica e : estatisticas) {
			e.setPorcentagem(totalRespostas);
		}

		mv.addObject("estatisticas", estatisticas);
		mv.addObject("total", totalRespostas);

		mv.addObject("httpServletRequest", httpServletRequest);
		return mv;
	}

}
