package com.projeto.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping(value="pauta")
public class PautaResource {
	
	@Autowired
	private PautaService service;

	@GetMapping
	public List<Pauta> buscarTodos() {
		return this.service.buscarTodos();
	}

	@GetMapping("/{id}")
	public Pauta buscarPorId(@PathVariable Long id) {
		return this.service.buscarPorId(id);
	}

	@PostMapping
	public Pauta salvar(@RequestBody Pauta pauta) {
		return this.service.salvar(pauta);
		
	}

	@PutMapping("/{id}")
	public Pauta atualizar(@PathVariable Long id, @RequestBody Pauta pauta) {
		return this.service.atualizar(id, pauta);
		
	}

	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		 this.service.excluir(id);
	}
}
