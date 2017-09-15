package com.ia.web.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.ia.web.model.ArquivoTreinamento;
import com.ia.web.model.BackPropagation;
import com.ia.web.model.Resposta;
import com.ia.web.model.resposta.Dificuldade;
import com.ia.web.model.resposta.Linguagem;
import com.ia.web.model.resposta.SimNao;
import com.ia.web.model.resposta.TempoAno;
import com.ia.web.service.BackPropagationService;
import com.ia.web.service.RespostaService;
import com.ia.web.service.StorageService;
import com.ia.web.storage.StorageFileNotFoundException;

@Controller
public class PrincipalController {

	@Autowired
	private BackPropagationService backPropagationService;

	@Autowired
	private RespostaService respostaService;
		
	private final StorageService storageService;

	@Autowired
	public PrincipalController(StorageService storageService) {
		this.storageService = storageService;
	}
	
	/**
	 * Boas vindas!
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping("/")
	public ModelAndView inicio() {

		ModelAndView mv = new ModelAndView("Principal");
		mv.addObject(new BackPropagation());

		return mv;
	}

	/**
	 * Recebe o treinamento por Ajax.
	 * 
	 * @param backPropagation
	 * @return String
	 */
	@RequestMapping(value = "/treinar", method = RequestMethod.POST)
	public @ResponseBody String treinar(@Validated BackPropagation backPropagation) {
		// , @RequestParam("file") MultipartFile arquivoTreinamento
//		System.out.println(arquivoTreinamento);
		System.out.println(backPropagation.getArquivoTreinamento());
//		System.out.println("File: " + backPropagation.getArquivoTreinamento().getOriginalFilename());		
//		storageService.store( backPropagation.getArquivoTreinamento() );

		
		try {
			backPropagationService.setBackPropagation(backPropagation);
			backPropagationService.treinar();
		} catch (Exception e) {
			e.printStackTrace();
			return e.getStackTrace().toString();
		}
		return "OK";
	}

	/**
	 * Reconhece as entradas do usuário.
	 * 
	 * @param backPropagation
	 * @param erros
	 * @param attributes
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView reconhecer(@Validated BackPropagation backPropagation, Errors erros,
			RedirectAttributes attributes) throws Exception {

		Dificuldade dificuldade = null;
		Resposta resposta;
		String resultado = "";

		
		ModelAndView mv = new ModelAndView("Principal");

		if (erros.hasErrors()) {
			return mv;
		}

		try {
			dificuldade = backPropagationService.reconhecer(backPropagation);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (dificuldade == null) {
			throw new Exception("Padrão não foi reconhecido!");
		}

		// Formata texto para exibir para o usuário.
		resultado = dificuldade.getTextUser(dificuldade);

		mv.addObject("mensagem", resultado);

		// Salva no banco de dados.
		resposta = new Resposta(backPropagation, dificuldade);
		respostaService.salvar(resposta);

		return mv;
	}

	@GetMapping("/teste")
	private String teste(BackPropagation backPropagation) {
		
//		ArquivoTreinamento arq = new ArquivoTreinamento();
		
		Gson gson =  new Gson();
//		System.out.println(gson.toJson(backPropagation));
		
//		System.out.println(gson.toJson(arq));
		
		String location = "user/sample_files"; 
		String fileName = "sample_training_data.json";
		String path = location + "/" + fileName;
		try {
		
			ClassPathResource classPathResource = new ClassPathResource(path);
				
			System.out.println(classPathResource.getFile());
			
		ClassLoader classLoader = getClass().getClassLoader();
		
//		System.out.println(classLoader.getResource(path));
		
		File file = new File(classLoader.getResource(path).getFile());
		
		System.out.println(file);
		}catch (Exception e) {
			e.printStackTrace();
		}
		//gson.fromJson(json, ArquivoTreinamento.class);
		
		return "Principal";
	}
	
	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc){
		return ResponseEntity.notFound().build();
	}
	
	@ModelAttribute("todasLinguagens")
	public List<Linguagem> todasLinguagens() {
		return Arrays.asList(Linguagem.values());
	}

	@ModelAttribute("todosTempoAno")
	public List<TempoAno> todosTempoAno() {
		return Arrays.asList(TempoAno.values());
	}

	@ModelAttribute("todosSimNao")
	public List<SimNao> todosSimNao() {
		return Arrays.asList(SimNao.values());
	}
}
