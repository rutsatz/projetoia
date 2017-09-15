package com.ia.web;

import java.math.BigInteger;
import java.util.Locale;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import com.ia.web.model.BackPropagation;
import com.ia.web.model.Resposta;
import com.ia.web.model.resposta.Dificuldade;
import com.ia.web.repository.Respostas;
import com.ia.web.service.StorageService;
import com.ia.web.storage.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class IAApplication {

	public static void main(String[] args) {
		SpringApplication.run(IAApplication.class, args);
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}
	
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}
	
	@Bean
	InitializingBean saveData(Respostas repo) {		
		return () -> {
			BackPropagation bPropagation = new BackPropagation();
			
			Resposta resposta = new Resposta(bPropagation, Dificuldade.FACIL);
			repo.save(resposta);
			
			resposta = new Resposta(bPropagation, Dificuldade.MODERADO);
			repo.save(resposta);
			
			resposta = new Resposta(bPropagation, Dificuldade.MODERADO);
			repo.save(resposta);
			
			resposta = new Resposta(bPropagation, Dificuldade.MODERADO);
			repo.save(resposta);
			
			resposta = new Resposta(bPropagation, Dificuldade.MODERADO);
			repo.save(resposta);

			resposta = new Resposta(bPropagation, Dificuldade.DIFICIL);
			repo.save(resposta);

			resposta = new Resposta(bPropagation, Dificuldade.DIFICIL);
			repo.save(resposta);
			
			resposta = new Resposta(bPropagation, Dificuldade.DIFICIL);
			repo.save(resposta);
			
			resposta = new Resposta(bPropagation, Dificuldade.DIFICIL);
			repo.save(resposta);
			
		};
		
	}
	
}
