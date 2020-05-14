package de.animebook.core.remote.series.model

import com.squareup.moshi.Json

data class Episode(
    val name: String,
    @Json(name = "episode_number") val position: String
)

data class ImageSet(
    @Json(name = "icon_url") val smallImage: String,
    @Json(name = "super_url") val largeImage: String
)

data class RemoteSeries(
    val name: String,
    val id: Int,
    @Json(name = "count_of_episodes") val episodeCount: Int,
    @Json(name = "deck") val shortDescription: String?,
    @Json(name = "description") val longDescription: String?,
    @Json(name = "first_episode") val firstEpisode: Episode?,
    @Json(name = "last_episode") val lastEpisode: Episode?,
    @Json(name = "image") val imageSet: ImageSet?
)

data class RemoteSeriesResult(
    @Json(name = "error") val status: String,
    @Json(name = "number_of_total_results") val resultCount: Int,
    @Json(name = "status_code") val statusCode: Int,
    @Json(name = "results") val remoteSeriesList: List<RemoteSeries>
)