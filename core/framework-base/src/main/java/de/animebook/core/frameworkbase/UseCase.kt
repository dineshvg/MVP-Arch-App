package de.animebook.core.frameworkbase

interface UseCase<out Result, in Params>  {

    suspend fun run(params: Params): Result
}