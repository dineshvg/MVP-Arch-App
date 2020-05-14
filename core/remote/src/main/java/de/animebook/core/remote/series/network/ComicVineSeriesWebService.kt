package de.animebook.core.remote.series.network

import de.animebook.core.remote.*
import de.animebook.core.remote.series.model.RemoteSeriesResult
import retrofit2.Call
import retrofit2.http.GET

interface ComicVineSeriesWebService {

    @GET(
        "/api/" + SERIES_SECTION +
                "?api_key=$API_KEY" + AND +
                "format=" + JSON + AND +
                "field_list=" + SERIES_FIELD_LIST + AND +
                "limit=" + LIMIT + AND +
                "sort=" + ASCENDING
    )
    fun retrieveSeries(): Call<RemoteSeriesResult>

}