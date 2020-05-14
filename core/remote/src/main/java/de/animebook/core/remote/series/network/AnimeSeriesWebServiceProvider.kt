package de.animebook.core.remote.series.network

import de.animebook.core.remote.RetrofitClient

object AnimeSeriesWebServiceProvider {

    val webService: ComicVineSeriesWebService
        get() = RetrofitClient() //todo: through DI
            .createRetrofitInstance()
            .create(ComicVineSeriesWebService::class.java)
}