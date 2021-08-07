package com.jj.kafkaproducer.config

import com.jj.kafkaproducer.model.Person
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class KafkaConfig {

    @Bean
    fun producerFactory(): ProducerFactory<String, Person> {
        val config: MutableMap<String, Any> = mutableMapOf()
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092")
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java)
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer::class.java)

        return DefaultKafkaProducerFactory(config)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, Person> {
        return KafkaTemplate(producerFactory())
    }
}