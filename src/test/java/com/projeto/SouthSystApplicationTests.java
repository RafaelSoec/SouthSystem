package com.projeto;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.projeto.model.dtos.ResultadoVotacaoDTO;
import com.projeto.model.entities.Associado;
import com.projeto.model.entities.Pauta;
import com.projeto.model.entities.Sessao;
import com.projeto.model.entities.Votacao;
import com.projeto.model.enums.VotoEnum;
import com.projeto.service.AssociadoService;
import com.projeto.service.PautaService;
import com.projeto.service.SessaoService;
import com.projeto.service.VotacaoService;

@SpringBootTest
class SouthSystApplicationTests {
	@Autowired
	private AssociadoService associadoService;

	@Autowired
	private SessaoService sessaoService;
	
	@Autowired
	private PautaService pautaService;

	@Autowired
	private VotacaoService votacaoService;

	@Test
	void contextLoads() {
		this.criarAssociado("04729646150");
		this.criarPauta();
		
		Sessao sessao = this.criarSessao();
		this.votar(sessao.getId());
	}
	
	Associado criarAssociado(String cpf) {
		Associado associado = new Associado();
		associado.setCpf(cpf);
		
		
		associado.setDataNascimento(new Date("01/01/1992"));
		associado.setNome("Rafael Souza");
		
		
		associado = this.associadoService.salvar(associado);
		
		assertNotEquals(associado.getId(), null);
		return associado;
	}
	
	Pauta criarPauta() {
		Pauta pauta = new Pauta();
		pauta.setDescricao("Nova pauta");
		pauta.setData(new Date());
		
		pauta = this.pautaService.salvar(pauta);
		
		assertNotEquals(pauta.getId(), null);
		
		return pauta;
	}

	Sessao criarSessao() {
		Pauta pauta = this.criarPauta();
		
		Date dataRef = new Date();
		Date dataFim = new Date(dataRef.getTime() + 60000);
		
		Sessao sessao = new Sessao();
		sessao.setDescricao("Nova sessao para a nova pauta");
		sessao.setDataInicio(dataRef);
		sessao.setDataFim(dataFim);
		sessao.setIdPauta(pauta.getId());
		
		sessao = this.sessaoService.salvar(sessao);
		
		assertNotEquals(sessao.getId(), null);
		return sessao;
	}

	void votar(Long idSessao) {
		String cpfAssociado = "04729646150";
		Votacao novaVotacao = new Votacao();
		Votacao voto = this.votacaoService.buscarPorAssociado(cpfAssociado);
		
		if(voto == null) {
			novaVotacao.setCpfAssociado(cpfAssociado);
			novaVotacao.setIdSessao(idSessao);
			novaVotacao.setVoto(VotoEnum.SIM);
		}
		
		this.votacaoService.votar(novaVotacao);
		
		assertNotEquals(novaVotacao.getId(), null);
		
		ResultadoVotacaoDTO result = this.sessaoService.recuperarResultado(idSessao);
		

		assertNotEquals(result.getValorTotalAFavor(), 1);
	}
	

}
