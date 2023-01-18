package com.jj.kafkaconsumer.db

import com.jj.kafkaconsumer.model.Person

class DbList {
    companion object {
        val people: MutableList<Person> = mutableListOf()
    }
}