package de.animebook.core.series.repo

class SeriesRepoImpl(
    private val remote: SeriesRemote
) : SeriesRepo {

    override suspend fun fetchSeriesList() = remote.fetchSeriesList()

}