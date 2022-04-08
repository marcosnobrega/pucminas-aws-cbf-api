package br.pucminas.aws.finalwork;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FinalworkApplication {

	public static final String topicExchangeName = "cbf-api-eventos-exchange";

	public static final String queueName = "cbf-api-eventos";

	public static final String queueMessagePrefix = "cbfapi.evento.";

	@Bean
	public Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(topicExchangeName);
	}

	@Bean
	public Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(queueMessagePrefix + "#");
	}

	public static void main(String[] args) {
		SpringApplication.run(FinalworkApplication.class, args);
	}

}
