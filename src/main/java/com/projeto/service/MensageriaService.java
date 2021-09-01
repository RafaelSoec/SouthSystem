package com.projeto.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.connection.RabbitMqConnection;

@Service
public class MensageriaService {

	@Autowired
	private RabbitMqConnection rabbitConn;
	
	@Autowired
	private ObjectMapper mapper;

	public void enviarMensagem(String nomeFila, Object mensagem) {
		try {
			RabbitTemplate rabbitTemp = new RabbitTemplate(this.rabbitConn.getConnection());
			String msg = this.mapper.writeValueAsString(mensagem);
			rabbitTemp.convertAndSend(nomeFila, msg);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Falha ao enviar mensagem na fila.");
		}
	}
	
}
