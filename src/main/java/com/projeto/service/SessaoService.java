package com.projeto.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.model.dtos.ResultadoVotacaoDTO;
import com.projeto.model.entities.Pauta;
import com.projeto.model.entities.Sessao;
import com.projeto.repository.SessaoRepository;

@Service
public class SessaoService extends AbstractService<Sessao> {


	@Autowired
	private SessaoRepository repository;
	
	@Autowired
	private PautaService pautaService;


	public Sessao iniciarSessao(Sessao sessao) {
		if (sessao == null) {
			throw new RuntimeException("Sessão não identificada.");
		}

		if(sessao.getDataInicio() == null) {
			throw new RuntimeException("Data de início da sessão não identicada.");
		}
		
		//definir como padrão - 1 minnuto a mais que a data de inicio
		if(sessao.getDataFim() == null) {
			long min = 60000;
			Date dataFim = new Date(sessao.getDataInicio().getTime() + min);
			sessao.setDataFim(dataFim);
		}

		Pauta pauta = this.pautaService.buscarPorId(sessao.getIdPauta());
		if (pauta == null) {
			throw new RuntimeException("Pauta não identificada.");
		}

		return this.salvar(sessao);
	}

	public ResultadoVotacaoDTO recuperarResultado(Long idSessao) {
		ResultadoVotacaoDTO result = new ResultadoVotacaoDTO();
		result.setValorTotal(this.repository.recuperarTotalVotos(idSessao));
		result.setValorTotalContra(this.repository.recuperarTotalVotosContra(idSessao));
		result.setValorTotalAFavor(this.repository.recuperarTotalVotosAFavor(idSessao));
		
		return result;
	}
}
