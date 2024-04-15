package com.ia.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SobreController {

	@GetMapping("/sobre")
	public ModelAndView sobre(HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("Sobre");
		mv.addObject("httpServletRequest", httpServletRequest);
		return mv;
	}

}
