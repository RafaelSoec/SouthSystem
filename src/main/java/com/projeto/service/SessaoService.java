package com.projeto.service;

import java.util.Date;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.connection.RabbitMqConnection;
import com.projeto.model.dtos.ResultadoVotacaoDTO;
import com.projeto.model.entities.Pauta;
import com.projeto.model.entities.Sessao;
import com.projeto.repository.SessaoRepository;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Service
public class SessaoService extends AbstractService<Sessao> {
	static final Logger logger = LogManager.getLogger(SessaoService.class.getName());


	@Autowired
	private SessaoRepository repository;
	
	@Autowired
	private PautaService pautaService;

	
	@Autowired
	private MensageriaService mensageriaService;
	

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

	@RabbitListener(queues = RabbitMqConnection.FILA_RESULTADO_SESSAO)
	public ResultadoVotacaoDTO recuperarResultado(Long idSessao) {
		ResultadoVotacaoDTO result = new ResultadoVotacaoDTO();
		result.setValorTotal(this.repository.recuperarTotalVotos(idSessao));
		result.setValorTotalContra(this.repository.recuperarTotalVotosContra(idSessao));
		result.setValorTotalAFavor(this.repository.recuperarTotalVotosAFavor(idSessao));
		
		logger.info("Resultado da sessão " + idSessao);
		logger.info("Total: " + result.getValorTotal());
		logger.info("Votos a Favor: " + result.getValorTotalAFavor());
		logger.info("Votos Contra: " + result.getValorTotalContra());
		
		return result;
	}

	public void encerrarSessao(Long idSessao) {
		if(idSessao == null) {
			throw new RuntimeException("Sessão não identificada.");
		}
		
		Sessao sessao = this.buscarPorId(idSessao);
		if (sessao == null) {
			throw new RuntimeException("Sessão não identificada.");
		}
		
		//caso a sessao esteja encerrada. Posta uma mensagem na fila
		Boolean sessaoEncerrada = this.verificarSessaoEncerrada(sessao);
		if(sessaoEncerrada) {
			ResultadoVotacaoDTO result = this.recuperarResultado(idSessao);
			this.mensageriaService.enviarMensagem(RabbitMqConnection.FILA_RESULTADO_SESSAO, result);
		}else {
			throw new RuntimeException("A Sessão ainda não pode ser encerrada.");
		}
	}
	
	public boolean verificarSessaoEncerrada(Sessao sessao) {
		Date dataRef = new Date();
		if(sessao.getDataFim().getTime() < dataRef.getTime()) {
			return true;
		}
		
		return false;
	}
}
