package com.projeto.service;

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

@Service
public class AssociadoService extends AbstractService<Associado>{
	

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
}


