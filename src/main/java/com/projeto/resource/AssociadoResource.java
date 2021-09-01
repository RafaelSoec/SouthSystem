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

import com.projeto.model.entities.Associado;
import com.projeto.model.entities.Votacao;
import com.projeto.service.AssociadoService;
import com.projeto.service.VotacaoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@Api(value = "API REST Associado")
@RequestMapping(value="associado")
public class AssociadoResource {
	
	@Autowired
	private AssociadoService service;
	
	@Autowired
	private VotacaoService votacaoService;

	@GetMapping
	@ApiOperation(value ="Busca todos os associados.")
	public List<Associado> buscarTodos() {
		return this.service.buscarTodos();
	}

	@GetMapping("/{id}")
	@ApiOperation(value ="Busca um associado por id.")
	public Associado buscarPorId(@PathVariable Long id) {
		return this.service.buscarPorId(id);
	}

	@PostMapping
	@ApiOperation(value ="Salva um associado.")
	public Associado salvar(@RequestBody Associado associado) {
		return this.service.salvar(associado);
		
	}

	@PutMapping("/{id}")
	@ApiOperation(value ="Atualiza os dados de um associado.")
	public Associado atualizar(@PathVariable Long id, @RequestBody Associado associado) {
		return this.service.atualizar(id, associado);
		
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value ="Exclui um associado.")
	public void excluir(@PathVariable Long id) {
		 this.service.excluir(id);
	}

	@PostMapping("votar")
	@ApiOperation(value ="Realiza o voto de um associado em uma sessão.")
	public void votar(@RequestBody Votacao voto) {
		this.votacaoService.votar(voto);
	}
}
