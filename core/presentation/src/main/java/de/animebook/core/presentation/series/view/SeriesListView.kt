package de.animebook.core.presentation.series.view

import de.animebook.core.presentation.series.model.PresentationSeries

interface SeriesListView {

    fun insertData(list: List<PresentationSeries>)

    fun showErrorScreen()

    fun showLoadingIndicator(visibility: Boolean)
}