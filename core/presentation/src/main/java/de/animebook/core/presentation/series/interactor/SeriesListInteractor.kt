package de.animebook.core.presentation.series.interactor

import de.animebook.core.presentation.series.mapper.PresentationSeriesMapper
import de.animebook.core.series.usecase.FetchAnimeSeriesUseCase

class SeriesListInteractor(
    private val fetchAnimeSeriesUseCase: FetchAnimeSeriesUseCase,
    private val mapper: PresentationSeriesMapper
) {

    suspend fun subscribeToSeries() =
        fetchAnimeSeriesUseCase.execute().map(mapper::mapTo)

}