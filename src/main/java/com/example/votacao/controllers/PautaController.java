package com.example.votacao.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.votacao.dto.AbrirSessaoVotacaoDTO;
import com.example.votacao.dto.CPFHabilitadoDTO;
import com.example.votacao.dto.ErroDTO;
import com.example.votacao.dto.PautaDTO;
import com.example.votacao.dto.ResultadoDTO;
import com.example.votacao.dto.VotoDTO;
import com.example.votacao.entity.Pauta;
import com.example.votacao.entity.Voto;
import com.example.votacao.entity.VotoPK;
import com.example.votacao.repository.PautaRepository;
import com.example.votacao.repository.VotoRepository;
import com.example.votacao.util.ValidarCPF;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import reactor.core.publisher.Mono;

@RestController
@Api(value = "Pauta")
@RequestMapping("/api/v1/pauta")
public class PautaController {

	@Autowired
	private PautaRepository pautaRepository;

	@Autowired
	private VotoRepository votoRepository;

	@ApiOperation(value = "Listar pautas")
	@GetMapping("/lista")
	public List<Pauta> getPautas() {
		return pautaRepository.findAll();
	}

	@ApiOperation(value = "Buscar pauta", notes = "Este endpoint busca uma pauta por id.")
	@ApiResponses({ @ApiResponse(code = 200, message = "Exclusão efetuada com sucesso"),
			@ApiResponse(code = 404, message = "Pauta não encontrada.") })
	@GetMapping("/{id}")
	public ResponseEntity<Pauta> getPauta(@PathVariable(value = "id") Integer id) {
		Optional<Pauta> pauta = pautaRepository.findById(id);

		if (pauta.isPresent()) {
			return new ResponseEntity<>(pauta.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Adicionar pauta", notes = "Este endpoint adicionar uma pauta.")
	@ApiResponses({ @ApiResponse(code = 201, message = "Pauta criada com sucesso.") })
	@PostMapping("/adicionar")
	public ResponseEntity<Object> adicionarPauta(@Valid @RequestBody PautaDTO pauta) {
		Pauta pautaGravar = new Pauta(pauta.getNome());
		pautaRepository.saveAndFlush(pautaGravar);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@ApiOperation(value = "Deletar pauta", notes = "Este endpoint exclui uma pauta e seus votos.")
	@ApiResponses({ @ApiResponse(code = 200, message = "Exclusão efetuada com sucesso"),
			@ApiResponse(code = 404, message = "Pauta não encontrada.") })
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

	@ApiOperation(value = "Abrir sessão de votação", notes = "Este endpoint abre a sessão de votação da pauta.")
	@ApiResponses({ @ApiResponse(code = 200, message = "Sessão de votação aberta com sucesso"),
			@ApiResponse(code = 404, message = "Pauta não encontrada.") })
	@PostMapping("/abrir-sessao-votacao")
	public ResponseEntity<Object> abrirSessaoVotacao(@Valid @RequestBody AbrirSessaoVotacaoDTO abrirSessaoVotacaoDTO) {
		Optional<Pauta> opPauta = pautaRepository.findById(abrirSessaoVotacaoDTO.getIdPauta());

		if (opPauta.isPresent()) {
			Pauta pauta = opPauta.get();

			if (pauta.getResultado() != null) {
				return erro("Pauta finalizada. Utilize o serviço de resultado.");
			}

			pauta.setDataAbertura(abrirSessaoVotacaoDTO.getDataAbertura());

			if (abrirSessaoVotacaoDTO.getDataFechamento() == null) {
				pauta.setDataFechamento(abrirSessaoVotacaoDTO.getDataAbertura().plusMinutes(1).withSecond(0));
			} else {
				pauta.setDataFechamento(abrirSessaoVotacaoDTO.getDataFechamento());
			}

			pautaRepository.saveAndFlush(pauta);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@ApiOperation(value = "Votar", notes = "Este endpoint regista o voto.")
	@ApiResponses({ @ApiResponse(code = 200, message = "Voto realizado com sucesso"),
			@ApiResponse(code = 404, message = "Pauta não encontrada.") })
	@PostMapping("/votar")
	public ResponseEntity<Object> votar(@Valid @RequestBody VotoDTO votoDTO) {

		if (!ValidarCPF.isCPF(votoDTO.getCpf())) {
			return erro("CPF inválido.");
		}
		
		if (!votoDTO.isVotoValido()) {
			return erro("Voto inválido! Apenas votos Sim ou Não são permitidos.");
		}

		WebClient client = WebClient.create("https://user-info.herokuapp.com");

		Mono<CPFHabilitadoDTO> servicoCPFHabilitado = client.get().uri("/users/" + votoDTO.getCpf()).retrieve()
				.bodyToMono(CPFHabilitadoDTO.class);

		CPFHabilitadoDTO cpfHabilitado = servicoCPFHabilitado.block();

		if (cpfHabilitado.isHabilitado()) {
			Optional<Pauta> opPauta = pautaRepository.findById(votoDTO.getIdPauta());

			if (opPauta.isPresent()) {
				Pauta pauta = opPauta.get();
				if (pauta.isAberta()) {

					VotoPK votoId = new VotoPK(votoDTO.getCpf(), votoDTO.getIdPauta());

					if (votoRepository.existsById(votoId)) {
						return erro("Voto não permitido. Apenas um voto por CPF permitido.");
					}

					Voto voto = new Voto(votoId, votoDTO.getVoto());
					votoRepository.saveAndFlush(voto);
					return new ResponseEntity<>(HttpStatus.CREATED);
				} else {
					return erro("Pauta fechada para votação.");
				}
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return erro("CPF não habilitado para votação");
		}

	}

	@ApiOperation(value = "Contabilizar votação", notes = "Este endpoint encerra a votação, caso ainda estaja aberta, e contabiliza os votos, apresentando o resultado.")
	@ApiResponses({ @ApiResponse(code = 200, message = "Votação contabilizada com sucesso"),
			@ApiResponse(code = 404, message = "Pauta não encontrada.") })
	@GetMapping("/{id}/contabilizar-votacao")
	public ResponseEntity<Object> contabilizarVotacao(@PathVariable(value = "id") Integer id) {
		Optional<Pauta> opPauta = pautaRepository.findById(id);

		if (opPauta.isPresent()) {

			Pauta pauta = opPauta.get();

			// pauta nao contabilizada
			if (pauta.getResultado() == null) {

				if (pauta.isAberta()) {
					pauta.setDataFechamento(LocalDateTime.now());
				}

				int qtdVotosSim = 0;
				int qtdVotosNao = 0;

				for (Voto voto : pauta.getVotos()) {
					if (voto.getVoto().equalsIgnoreCase("SIM")) {
						qtdVotosSim += 1;
					} else {
						qtdVotosNao += 1;
					}
				}

				if (qtdVotosSim == 0 && qtdVotosNao == 0) {
					// pauta sem votos
					return erro("Contabilização não permitida, pauta não foi votada.");
				}

				pauta.setQtdVotosSim(qtdVotosSim);
				pauta.setQtdVotosNao(qtdVotosNao);

				if (qtdVotosSim > qtdVotosNao) {
					pauta.setResultado("SIM");
				} else if (qtdVotosSim < qtdVotosNao) {
					pauta.setResultado("NÃO");
				} else {
					pauta.setResultado("EMPATE");
				}

				pautaRepository.saveAndFlush(pauta);

				ResultadoDTO resultado = new ResultadoDTO(qtdVotosNao, qtdVotosSim, pauta.getResultado());

				return new ResponseEntity<>(resultado, HttpStatus.OK);
			} else {
				// pauta contabilizada
				return erro("Pauta contabilizada, utilize o seviço de consultar resultado.");
			}

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Resultado da votação", notes = "Este endpoint exibe o resultado da votação se já tiver sido contabilizada.")
	@ApiResponses({ @ApiResponse(code = 404, message = "Pauta não encontrada.") })
	@GetMapping("/{id}/resultado-votacao")
	public ResponseEntity<Object> resultado(@PathVariable(value = "id") Integer id) {
		Optional<Pauta> opPauta = pautaRepository.findById(id);

		if (opPauta.isPresent()) {

			Pauta pauta = opPauta.get();

			if (pauta.getResultado() != null) {

				ResultadoDTO resultado = new ResultadoDTO(pauta.getQtdVotosNao(), pauta.getQtdVotosSim(),
						pauta.getResultado());
				return new ResponseEntity<>(resultado, HttpStatus.OK);

			} else {

				if (pauta.isAberta()) {
					return erro(
							"Pauta aberta para votação, utilize o seviço de contabilizar votação para encerrar votação e obter o resultado.");
				} else {
					return erro("Utilize o seviço de contabilizar votação para encerrar e obter o resultado.");
				}

			}

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	private ResponseEntity<Object> erro(String mensagemErro) {
		return new ResponseEntity<>(new ErroDTO(mensagemErro), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

}
