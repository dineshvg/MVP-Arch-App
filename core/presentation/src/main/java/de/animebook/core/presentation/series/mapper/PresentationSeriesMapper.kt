package de.animebook.core.presentation.series.mapper

import de.animebook.core.frameworkbase.Mapper
import de.animebook.core.presentation.series.model.PresentationSeries
import de.animebook.core.series.model.Series

class PresentationSeriesMapper : Mapper<Series, PresentationSeries> {

    override fun mapTo(from: Series) =
        PresentationSeries(
            name = from.name,
            id = from.id,
            episodes = from.episodeCount,
            shortDescription = from.shortDescription ?: MISSING,
            longDescription = from.longDescription ?: MISSING,
            firstEpisodeName = from.firstEpisodeName ?: FILL_EMPTY,
            firstEpisodePosition = from.firstEpisodePosition ?: FILL_EMPTY,
            lastEpisodeName = from.lastEpisodeName ?: FILL_EMPTY,
            lastEpisodePosition = from.lastEpisodePosition ?: FILL_EMPTY,
            listImagePresent = from.smallImage != null,
            detailImagePresent = from.largeImage != null,
            smallImage = from.smallImage,
            largeImage = from.largeImage
        )

    companion object {
        private const val MISSING = "Description missing in comic-vine"
        private const val FILL_EMPTY = "---"
    }

}