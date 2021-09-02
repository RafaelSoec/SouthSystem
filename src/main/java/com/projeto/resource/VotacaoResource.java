package com.projeto.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.model.entities.Votacao;
import com.projeto.service.VotacaoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@Api(value = "API REST Votação")
@RequestMapping(value="votacao")
public class VotacaoResource {
	
	@Autowired
	private VotacaoService service;

	@GetMapping
	@ApiOperation(value ="Busca todos os votos.")
	public List<Votacao> buscarTodos() {
		return this.service.buscarTodos();
	}

	@GetMapping("/{id}")
	@ApiOperation(value ="Busca um voto por id.")
	public Votacao buscarPorId(@PathVariable Long id) {
		return this.service.buscarPorId(id);
	}

	@PostMapping
	@ApiOperation(value ="Salva um voto.")
	public Votacao salvar(@RequestBody Votacao votacao) {
		return this.service.salvar(votacao);
		
	}

	@PutMapping("/{id}")
	@ApiOperation(value ="Atualiza um voto.")
	public Votacao atualizar(@PathVariable Long id, @RequestBody Votacao voto) {
		return this.service.atualizar(id, voto);
		
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value ="Exclui um voto.")
	public void excluir(@PathVariable Long id) {
		 this.service.excluir(id);
	}
}
