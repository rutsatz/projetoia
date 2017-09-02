package com.ia.web.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ia.web.model.Aprendizado;
import com.ia.web.model.BackPropagation;
import com.ia.web.model.Linguagem;

@Controller
public class PrincipalController {

	
	@RequestMapping("/")
	public ModelAndView inicio(@Autowired @Validated BackPropagation backPropagation, Errors erros, RedirectAttributes attributes) {
		
		ModelAndView mv = new ModelAndView("Principal");
//		mv.addObject(new BackPropagation());
		mv.addObject("backpropagation", backPropagation);
		
		return mv;
	}

	@RequestMapping(value = "/reconhecer", method = RequestMethod.POST)
	public @ResponseBody String reconhecer(@Validated BackPropagation backPropagation, Errors erros, RedirectAttributes attributes) {
		System.out.println("tem erros: " + erros.hasErrors());
		if(erros.hasErrors()) {
			return "redirect:/";
		}
		
		
		
		System.out.println(backPropagation.getDificuldadeAprendizado());
		
		return "Principal";
	}

	@RequestMapping(value = "/treinar", method = RequestMethod.POST)
	public @ResponseBody String treinar() {
		
		System.out.println("Treinar");
		
		return "RNA treinada com sucesso!"; 
	}
	
	@ModelAttribute("todasLinguagens")
	public List<Linguagem> todasLinguagens(){
		return Arrays.asList(Linguagem.values());
	}
	
	@ModelAttribute("todasDificuldadesAprendizado")
	public List<Aprendizado> todasDificuldadesAprendizado(){
		return Arrays.asList(Aprendizado.values());
	}
	
}
