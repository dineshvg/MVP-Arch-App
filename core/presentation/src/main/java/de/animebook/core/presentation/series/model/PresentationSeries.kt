package de.animebook.core.presentation.series.model

data class PresentationSeries(
    val id: Int,
    val name: String,
    val episodes: Int,
    val shortDescription: String,
    val longDescription: String,
    val firstEpisodeName: String,
    val firstEpisodePosition: String,
    val lastEpisodeName: String,
    val lastEpisodePosition: String,
    val listImagePresent: Boolean,
    val detailImagePresent: Boolean,
    val smallImage: String?,
    val largeImage: String?
)