package com.jj.kafkaconsumer.config

import com.jj.kafkaconsumer.model.Person
import com.jj.kafkaconsumer.db.DbList.Companion.people
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaListener(val kafkaTemplate: KafkaTemplate<String, Person>) {

    @KafkaListener(topics = ["kafka-topic"])
    fun listener(person: Person) {
        people.add(person)
        println("Person: $person")
    }
}