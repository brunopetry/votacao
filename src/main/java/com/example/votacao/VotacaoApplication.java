package com.example.votacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class VotacaoApplication {
	
	@RequestMapping("/")
    public String home() {
        return "Bem vindo ao sistema de votação!";
    }


	public static void main(String[] args) {
		SpringApplication.run(VotacaoApplication.class, args);
	}

}
