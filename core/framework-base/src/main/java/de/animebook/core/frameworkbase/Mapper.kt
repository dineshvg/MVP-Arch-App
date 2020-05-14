package de.animebook.core.frameworkbase

interface Mapper<in From, out To> {

    fun mapTo(from: From): To
}