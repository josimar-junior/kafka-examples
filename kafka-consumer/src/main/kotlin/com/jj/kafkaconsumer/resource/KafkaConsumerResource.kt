package com.jj.kafkaconsumer.resource

import com.jj.kafkaconsumer.db.DbList.Companion.people
import com.jj.kafkaconsumer.model.Person
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/consumer")
class KafkaConsumerResource {

    @GetMapping
    fun people(): ResponseEntity<List<Person>> {
        return ResponseEntity.ok(people)
    }
}