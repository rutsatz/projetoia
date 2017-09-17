package com.ia.web.controller;

import java.io.FileInputStream;

import java.io.IOException;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@RequestMapping(value = "/user/sample_files/{filename:.+}", method = RequestMethod.GET)
	public void getDownload(@PathVariable String filename, HttpServletResponse response) throws IOException {

		try {
			FileInputStream file = new FileInputStream("user/sample_files/" + filename);

			// Set the content type and attachment header.
			response.addHeader("Content-disposition", "attachment;filename=" + filename);
			response.setContentType("application/json");

			// Copy the stream to the response's output stream.
			IOUtils.copy(file, response.getOutputStream());
			response.flushBuffer();

		} catch (IOException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
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

	public StorageService getStorageService() {
		return storageService;
	}
}
