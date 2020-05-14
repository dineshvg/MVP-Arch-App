package de.animebook.feature.series.di

import de.animebook.core.presentation.series.interactor.SeriesListInteractor
import de.animebook.core.presentation.series.mapper.PresentationSeriesMapper
import de.animebook.core.presentation.series.presenter.SeriesListPresenter
import de.animebook.core.presentation.series.presenter.SeriesListPresenterImpl
import de.animebook.feature.series.ui.adapter.SeriesAdapterFactory
import org.rewedigital.katana.Module
import org.rewedigital.katana.dsl.factory
import org.rewedigital.katana.dsl.get

val SeriesPresentationModule = Module(
    name = "SeriesPresentationModule"
) {
    factory { PresentationSeriesMapper() }

    factory { SeriesAdapterFactory() }

    factory { SeriesListInteractor(get(), get()) }

    factory<SeriesListPresenter> { SeriesListPresenterImpl(get()) }
}