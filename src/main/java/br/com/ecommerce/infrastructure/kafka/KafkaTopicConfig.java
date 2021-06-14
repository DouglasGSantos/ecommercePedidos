package br.com.ecommerce.infrastructure.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin.NewTopics;

@Configuration
class KafkaTopicConfig {

	@Bean
	public NewTopics topics() {
		KafkaTopics[] kafkaTopics = KafkaTopics.values();
		NewTopic[] topics = new NewTopic[kafkaTopics.length];
		int i = 0;
		for (KafkaTopics topic : kafkaTopics) {
			topics[i] = new NewTopic(topic.toString(), 1, (short) 1);
			i++;
		}

		return new NewTopics(topics);
	}



}