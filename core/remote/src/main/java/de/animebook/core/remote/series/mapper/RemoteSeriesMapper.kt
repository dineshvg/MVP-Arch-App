package de.animebook.core.remote.series.mapper

import de.animebook.core.frameworkbase.Mapper
import de.animebook.core.remote.series.model.RemoteSeries
import de.animebook.core.series.model.Series
import de.animebook.core.util.Category

class RemoteSeriesMapper : Mapper<RemoteSeries, Series> {

    override fun mapTo(from: RemoteSeries) =
        Series(
            name = from.name,
            id = from.id,
            episodeCount = from.episodeCount,
            shortDescription = from.shortDescription,
            longDescription = from.longDescription,
            smallImage = from.imageSet?.smallImage,
            largeImage = from.imageSet?.largeImage,
            firstEpisodeName = from.firstEpisode?.name,
            lastEpisodeName = from.lastEpisode?.name,
            firstEpisodePosition = from.firstEpisode?.position,
            lastEpisodePosition = from.lastEpisode?.position,
            category = Category.SERIES
        )
}