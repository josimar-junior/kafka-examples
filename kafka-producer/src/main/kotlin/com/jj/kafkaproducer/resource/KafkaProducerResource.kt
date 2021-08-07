package com.jj.kafkaproducer.resource

import com.jj.kafkaproducer.model.Person
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/producer")
class KafkaProducerResource(val kafkaTemplate: KafkaTemplate<String, Person>) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody person: Person): Person {
        kafkaTemplate.send("kafka-topic", person)
        return person
    }
}