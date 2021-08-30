package com.projeto.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.model.entities.Pasta;
import com.projeto.service.PastaService;

@RestController
@RequestMapping(value="pastas")
public class PautaResource {
	
	@Autowired
	private PastaService service;

	@RequestMapping(method=RequestMethod.GET)
	public List<Pasta> todos() {
		return this.service.buscarTodos();
	}

	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public Pasta recuperarPorId(@PathVariable Long id) {
		return this.service.buscarPorId(id);
	}

	@RequestMapping(method=RequestMethod.POST)
	public Pasta salvar(Pasta pasta) {
		return this.service.salvar(pasta);
		
	}

	@RequestMapping(method=RequestMethod.PUT, value="/{id}")
	public Pasta atualizar(@PathVariable Long id, Pasta pasta) {
		return this.service.atualizar(id, pasta);
		
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public void excluir(@PathVariable Long id) {
		 this.service.buscarPorId(id);
	}
}
