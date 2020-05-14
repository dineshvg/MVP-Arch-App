package de.animebook.core.series.usecase

import de.animebook.core.series.repo.SeriesRepo

class FetchAnimeSeriesUseCase(private val repository: SeriesRepo) {

    suspend fun execute() = repository.fetchSeriesList()
    
}