package de.animebook.core.frameworkbase

interface DuplexMapper<From, To> : Mapper<From, To> {

    fun mapFrom(to: To): From
}