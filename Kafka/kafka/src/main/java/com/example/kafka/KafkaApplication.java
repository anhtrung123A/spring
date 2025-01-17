package com.example.kafka;

import com.example.kafka.domain.CustomerVisitEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication
public class KafkaApplication {
	@Autowired
	private ObjectMapper objectMapper;

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}
	@Bean
	public ApplicationRunner runner(KafkaTemplate<String, String> kafkaTemplate) throws JsonProcessingException {
		final CustomerVisitEvent event = CustomerVisitEvent.builder()
				.customerID(UUID.randomUUID().toString())
				.dateTime(LocalDateTime.now())
				.build();
		final String payload = objectMapper.writeValueAsString(event);
		return args -> {
			kafkaTemplate.send("customer.visit", payload);
		};
	}
	@KafkaListener(topicPattern = "customer.visit")
	public String listen(final String in){
		System.out.println(in);
		return in;
	}
}
