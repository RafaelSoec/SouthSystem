package com.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.model.entities.Sessao;
import com.projeto.model.entities.Votacao;
import com.projeto.model.enums.VotoEnum;
import com.projeto.repository.VotacaoRepository;

@Service
public class VotacaoService extends AbstractService<Votacao> {

	@Autowired
	private VotacaoRepository repository;
	
	@Autowired
	private SessaoService sessaoService;


	public Votacao buscarPorAssociado(String cpf) {
		return this.repository.buscarPorAssociado(cpf);
	}


	public void votar(Votacao voto) {
		if (voto == null || voto.getVoto() == VotoEnum.BRANCO) {
			throw new RuntimeException("Voto não identificado.");
		}

		if (voto.getIdSessao() == null) {
			throw new RuntimeException("Sessão não identificada.");
		}

		Sessao sessao = this.sessaoService.buscarPorId(voto.getIdSessao());
		if (sessao == null) {
			throw new RuntimeException("Sessão não identificada.");
		}
		
		if(voto.getCpfAssociado() == null) {
			throw new RuntimeException("Cpf do associado não identificado.");
		}
		
		Boolean sessaoEncerrada = this.sessaoService.verificarSessaoEncerrada(sessao);
		if(sessaoEncerrada) {
			throw new RuntimeException("Período de votação encerrado.");
		}

		Votacao votacaoAnt = this.repository.buscarPorAssociado(voto.getCpfAssociado());
		if(votacaoAnt != null) {
			throw new RuntimeException("O associado só pode votar uma vez por pauta.");
		}
		
		this.salvar(voto);
	}
	
}
