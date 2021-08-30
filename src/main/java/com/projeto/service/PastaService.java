package com.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.model.entities.Pasta;
import com.projeto.repository.PastaRepository;

@Service
public class PastaService {
	
	@Autowired
	private PastaRepository repository;
	
	public Pasta buscarPorId(Long id){
		if(id == null) {
			throw new RuntimeException("Falta ao tentar recuperar a pasta: Id nulo.");
		}
		
		Optional<Pasta> pasta = this.repository.findById(id);
		return pasta.get();
	}
	
	public List<Pasta> buscarTodos(){
		return this.repository.findAll();
	}

	public Pasta salvar(Pasta pasta){
		if(pasta == null) {
			throw new RuntimeException("Falha ao tentar salvar a pasta: Pasta nula.");
		}
		
		return this.repository.save(pasta);
	}

	public Pasta atualizar(Long id, Pasta pasta){
		Pasta pastaRec = this.buscarPorId(id);
		pastaRec = pasta;
		
		return this.salvar(pastaRec);
	}

	public void excluir(Long id ){
		Pasta pastaRec = this.buscarPorId(id);
		this.repository.delete(pastaRec);
	}
}
