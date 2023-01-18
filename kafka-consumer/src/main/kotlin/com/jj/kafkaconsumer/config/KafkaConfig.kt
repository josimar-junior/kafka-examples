package com.jj.kafkaconsumer.config

import com.jj.kafkaconsumer.model.Person
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer

@Configuration
@EnableKafka
class KafkaConfig {

    @Bean
    fun consumerFactory(): ConsumerFactory<String, Person> {
        val config: MutableMap<String, Any> = mutableMapOf()
        config[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:29092"
        config[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        config[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java
        config[ConsumerConfig.GROUP_ID_CONFIG] = "groupId"

        val jsonDeserializer = JsonDeserializer(Person::class.java)
        jsonDeserializer.setRemoveTypeHeaders(false);
        jsonDeserializer.addTrustedPackages("*");
        jsonDeserializer.setUseTypeMapperForKey(true);

        return DefaultKafkaConsumerFactory(config, StringDeserializer(), jsonDeserializer)
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Person> {
        val concurrentKafkaListenerContainerFactory: ConcurrentKafkaListenerContainerFactory<String, Person> = ConcurrentKafkaListenerContainerFactory()
        concurrentKafkaListenerContainerFactory.consumerFactory = consumerFactory()
        return concurrentKafkaListenerContainerFactory
    }
}