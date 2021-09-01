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

import com.projeto.model.entities.Pauta;
import com.projeto.service.PautaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@Api(value = "API REST Pauta")
@RequestMapping(value="pauta")
public class PautaResource {
	
	@Autowired
	private PautaService service;

	@GetMapping
	@ApiOperation(value ="Busca todos as Pautas.")
	public List<Pauta> buscarTodos() {
		return this.service.buscarTodos();
	}

	@GetMapping("/{id}")
	@ApiOperation(value ="Busca uma pauta por id.")
	public Pauta buscarPorId(@PathVariable Long id) {
		return this.service.buscarPorId(id);
	}

	@PostMapping
	@ApiOperation(value ="Salva uma pauta.")
	public Pauta salvar(@RequestBody Pauta pauta) {
		return this.service.salvar(pauta);
		
	}

	@PutMapping("/{id}")
	@ApiOperation(value ="Atualiza uma pauta.")
	public Pauta atualizar(@PathVariable Long id, @RequestBody Pauta pauta) {
		return this.service.atualizar(id, pauta);
		
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value ="Exclui uma pauta.")
	public void excluir(@PathVariable Long id) {
		 this.service.excluir(id);
	}
}
