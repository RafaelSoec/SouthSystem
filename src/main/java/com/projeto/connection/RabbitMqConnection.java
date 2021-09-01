package com.projeto.connection;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqConnection {
	public static final String FILA_RESULTADO_SESSAO = "result_session";
	private static final String RABBIT_DIRECT = "amq.direct";
	private CachingConnectionFactory connectionFactory;
	private AmqpAdmin adminAmqp;

	@Value("${rabbit.host}")
	private String host;

	@Value("${rabbit.username}")
	private String user;

	@Value("${rabbit.password}")
	private String password;

	public CachingConnectionFactory getConnection() {
		if (this.connectionFactory == null) {
			this.connectionFactory = new CachingConnectionFactory(this.host);
			this.connectionFactory.setUsername(this.user);
			this.connectionFactory.setPassword(this.password);
			this.connectionFactory.setVirtualHost(this.user);
			
			// Recommended settings
			this.connectionFactory.setRequestedHeartBeat(30);
			this.connectionFactory.setConnectionTimeout(30000);
		}

		return this.connectionFactory;
	}


	private void conectarRabbitMQ() {
		CachingConnectionFactory connection = this.getConnection();
		this.adminAmqp = new RabbitAdmin(connection);
	}

	private Queue recuperarFila(String nomeFila) {
		return new Queue(nomeFila, true, false, false);
	}

	// Usando o Direct
	private DirectExchange realizarTrocaDireta() {
		return new DirectExchange(RABBIT_DIRECT);
	}

	private Binding relacionarFila(Queue fila, DirectExchange exch) {
		return new Binding(fila.getName(), Binding.DestinationType.QUEUE, exch.getName(), fila.getName(), null);
	}

	public void adicionarFila(String nomeFila) {
		Queue fila = this.recuperarFila(nomeFila);
		DirectExchange exch = this.realizarTrocaDireta();
		Binding relacionamento = this.relacionarFila(fila, exch);

		this.adminAmqp.declareQueue(fila);
		this.adminAmqp.declareExchange(exch);
		this.adminAmqp.declareBinding(relacionamento);
	}
	
	@PostConstruct
	private void adicionarFilas() {
		this.conectarRabbitMQ();
		this.adicionarFila(FILA_RESULTADO_SESSAO);
	}

}
