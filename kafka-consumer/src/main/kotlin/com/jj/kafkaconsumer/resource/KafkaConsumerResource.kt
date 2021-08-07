package com.jj.kafkaconsumer.resource

import com.jj.kafkaconsumer.model.Person
import org.springframework.http.ResponseEntity
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/consumer")
class KafkaConsumerResource(val kafkaTemplate: KafkaTemplate<String, Person>) {

    @KafkaListener(topics = ["kafka-topic"])
    fun addPerson(person: Person) {
        people.add(person)
        println(person.name + " - " + person.age)
    }

    @GetMapping
    fun people(): ResponseEntity<List<Person>> {
        return ResponseEntity.ok(people)
    }

    companion object People {
        val people: ArrayList<Person> = ArrayList()
    }
}