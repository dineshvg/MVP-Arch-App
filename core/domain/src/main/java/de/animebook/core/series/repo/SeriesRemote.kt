package de.animebook.core.series.repo

import de.animebook.core.series.model.Series

interface SeriesRemote {

    suspend fun fetchSeriesList(): List<Series>
}