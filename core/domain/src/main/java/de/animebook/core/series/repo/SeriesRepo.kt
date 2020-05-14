package de.animebook.core.series.repo

import de.animebook.core.series.model.Series

interface SeriesRepo {

    suspend fun fetchSeriesList(): List<Series>
}