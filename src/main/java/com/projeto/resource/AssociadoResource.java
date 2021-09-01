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

import com.projeto.model.entities.Associado;
import com.projeto.model.entities.Votacao;
import com.projeto.service.AssociadoService;
import com.projeto.service.VotacaoService;

@RestController
@RequestMapping(value="associado")
public class AssociadoResource {
	
	@Autowired
	private AssociadoService service;
	
	@Autowired
	private VotacaoService votacaoService;

	@GetMapping
	public List<Associado> buscarTodos() {
		return this.service.buscarTodos();
	}

	@GetMapping("/{id}")
	public Associado buscarPorId(@PathVariable Long id) {
		return this.service.buscarPorId(id);
	}

	@PostMapping
	public Associado salvar(@RequestBody Associado associado) {
		return this.service.salvar(associado);
		
	}

	@PutMapping("/{id}")
	public Associado atualizar(@PathVariable Long id, @RequestBody Associado associado) {
		return this.service.atualizar(id, associado);
		
	}

	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		 this.service.excluir(id);
	}

	@PostMapping("votar")
	public void votar(@RequestBody Votacao voto) {
		boolean podeVotar = this.service.verificarSeAssociadoPodeVotar(voto.getCpfAssociado());
		
		if(podeVotar) {
			this.votacaoService.votar(voto);
		}else {
			throw new RuntimeException("Associado não habilitado a votar.");
		}
	}
}
