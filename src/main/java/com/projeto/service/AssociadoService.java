package com.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.projeto.exception.ResponseException;
import com.projeto.model.dtos.StatusVotoDTO;
import com.projeto.model.entities.Associado;
import com.projeto.model.enums.StatusVotoEnum;
import com.projeto.repository.AssociadoRepository;

@Service
public class AssociadoService extends AbstractService<Associado>{
	
	@Autowired
	private AssociadoRepository repository;

	public boolean verificarSeAssociadoPodeVotar(String cpf) {
		//String urlApiExt = "https://user-info.herokuapp.com/users/" + cpf;
		RestTemplate template = new RestTemplate();
		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host("user-info.herokuapp.com")
				.path("users/" + cpf)
				.build();
		
		ResponseEntity<StatusVotoDTO> entity = template.getForEntity(uri.toUriString(), StatusVotoDTO.class);
		
		if(entity.getStatusCode() != HttpStatus.OK) {
			throw new ResponseException("CPF inválido");
		}
		
		String status = entity.getBody().getStatus();
		return status.equals(StatusVotoEnum.ABLE_TO_VOTE.getStatus());
	}
	

	public Associado salvar(Associado associado){
		if(associado == null) {
			throw new ResponseException("Falha ao tentar salvar: Associado nula.");
		}
		
		if(associado.getCpf() == null) {
			throw new ResponseException("CPF inválido.");
		}
		
		boolean cpfExiste = this.validarCpfDuplicado(associado.getCpf());
		if(cpfExiste) {
			throw new ResponseException("CPF já cadastrado.");
		}
		
		return super.salvar(associado);
	}
	
	//retorna true se o cpf do associado for duplicado
	public boolean validarCpfDuplicado(String cpf) {
		Associado associado = this.repository.recuperarAssociadoPorCpf(cpf);
		
		if(associado != null) {
			return true;
		}
		
		return false;
	}
	

	public Associado recuperarAssociadoPorCpf(String cpf) {
		return this.repository.recuperarAssociadoPorCpf(cpf);
	}
}


