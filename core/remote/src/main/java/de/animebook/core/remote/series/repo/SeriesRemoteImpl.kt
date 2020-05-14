package de.animebook.core.remote.series.repo

import de.animebook.core.remote.series.mapper.RemoteSeriesMapper
import de.animebook.core.remote.series.network.ComicVineSeriesWebService
import de.animebook.core.series.model.Series
import de.animebook.core.series.repo.SeriesRemote
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * URL CALLED: https://comicvine.gamespot.com/api/series_list/
?api_key=62bb0272a8b6de67d6c4f75b009a359797671113
&format=json&field_list=deck,description,first_episode,last_episode,count_of_episodes,image
&limit=50&sort=field:asc
 */

class SeriesRemoteImpl(
    private val mapper: RemoteSeriesMapper,
    private val webService: ComicVineSeriesWebService
) : SeriesRemote {

    override suspend fun fetchSeriesList() =
        suspendCoroutine<List<Series>> { continuation ->
            try {
                webService
                    .retrieveSeries()
                    .enqueue { result ->
                        when (result) {
                            is Result.Success -> {
                                result.response.body()?.let { remoteSeriesResult ->
                                    remoteSeriesResult.remoteSeriesList.map(mapper::mapTo)
                                        .let { domainSeriesList ->
                                            continuation.resume(domainSeriesList)
                                        }
                                }
                            }
                            is Result.Failure -> {
                                print("error on retrieving series list: ${result.error.printStackTrace()}")
                                continuation.resume(emptyList())
                            }
                        }
                    }
            } catch (e: Exception) {
                print("error on retrieving series list: ${e.stackTrace}")
                continuation.resume(emptyList())
            }
        }


    private inline fun <reified T> Call<T>.enqueue(crossinline result: (Result<T>) -> Unit) {
        enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, error: Throwable) {
                result(Result.Failure(call, error))
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                result(Result.Success(call, response))
            }
        })
    }

    private sealed class Result<T> {
        data class Success<T>(val call: Call<T>, val response: Response<T>) : Result<T>()
        data class Failure<T>(val call: Call<T>, val error: Throwable) : Result<T>()
    }
}


