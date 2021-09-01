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

import com.projeto.model.dtos.ResultadoVotacaoDTO;
import com.projeto.model.entities.Sessao;
import com.projeto.service.SessaoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@Api(value = "API REST Sessao")
@RequestMapping(value="sessao")
public class SessaoResource {
	
	@Autowired
	private SessaoService service;

	@GetMapping
	@ApiOperation(value ="Busca todas as sessões.")
	public List<Sessao> buscarTodos() {
		return this.service.buscarTodos();
	}

	@GetMapping("/{id}")
	@ApiOperation(value ="Busca uma sessão por id.")
	public Sessao buscarPorId(@PathVariable Long id) {
		return this.service.buscarPorId(id);
	}
	
	@GetMapping("resultado/{id}")
	@ApiOperation(value ="Recupera o resultado de uma sessão.")
	public ResultadoVotacaoDTO recuperarResultado(@PathVariable Long id) {
		return this.service.recuperarResultado(id);
	}


	@PutMapping("/{id}")
	@ApiOperation(value ="Atualiza uma sessão.")
	public Sessao atualizar(@PathVariable Long id, @RequestBody Sessao sessao) {
		return this.service.atualizar(id, sessao);
		
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value ="Exclui uma sessão.")
	public void excluir(@PathVariable Long id) {
		 this.service.excluir(id);
	}
	

	@PostMapping
	@ApiOperation(value ="Inicia uma sessão.")
	public Sessao iniciarSessao(@RequestBody Sessao sessao) {
		return this.service.iniciarSessao(sessao);
		
	}

	@GetMapping("finalizar/{id}")
	@ApiOperation(value ="Finaliza uma sessão.")
	public void encerrarSessao(@PathVariable Long id) {
		 this.service.encerrarSessao(id);
		
	}
}
