package com.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.model.entities.Pauta;
import com.projeto.repository.PautaRepository;

@Service
public class PautaService extends AbstractService<Pauta>{
	
	@Autowired
	private PautaRepository repository;
	
}
