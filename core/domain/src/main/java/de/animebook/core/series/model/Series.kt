package de.animebook.core.series.model

import de.animebook.core.util.Category

data class Series(
    val id:Int,
    val name: String,
    val episodeCount: Int,
    val shortDescription: String?,
    val longDescription: String?,
    val firstEpisodeName: String?,
    val firstEpisodePosition: String?,
    val lastEpisodeName: String?,
    val lastEpisodePosition: String?,
    val smallImage: String?,
    val largeImage: String?,
    val category: Category
)