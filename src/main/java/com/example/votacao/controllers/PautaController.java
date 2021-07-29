package com.example.votacao.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.votacao.dto.PautaDTO;
import com.example.votacao.entity.Pauta;
import com.example.votacao.repository.PautaRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Pauta")
@RequestMapping("/api/pauta")
public class PautaController {

	@Autowired
	private PautaRepository pautaRepository;
	
	@ApiOperation(value = "Mostra lista de pautas")
	@GetMapping("/lista")
	public List<Pauta> getPautas() {
		return pautaRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pauta> getPauta(@PathVariable(value = "id") Integer id) {
		Optional<Pauta> pauta = pautaRepository.findById(id);
		
		if (pauta.isPresent()) {
			return new ResponseEntity<>(pauta.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/adicionar")
	public Pauta inserirPauta(@Valid @RequestBody PautaDTO pauta) {
		Pauta pautaGravar = new Pauta(pauta.getNome(), new Date());
		return pautaRepository.saveAndFlush(pautaGravar);
	}

//	@RequestMapping(value = "/pauta/atualizarSenha", method = RequestMethod.POST)
//	public ResponseEntity<Object> atualizarSenha(@Valid @RequestBody UsuarioDTO pauta) {
//		Optional<Pauta> usuarioBD = pautaRepository.findByEmail(pauta.getEmail());
//		if (usuarioBD.isPresent()) {
//			usuarioBD.get().setSenha(passwordEncoder.encode(pauta.getSenha()));
//			pautaRepository.save(usuarioBD.get());
//			return new ResponseEntity<>(HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		
//	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletarPauta(@PathVariable(value = "id") Integer id) {
		Optional<Pauta> pauta = pautaRepository.findById(id);
		if (pauta.isPresent()) {
			pautaRepository.delete(pauta.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
