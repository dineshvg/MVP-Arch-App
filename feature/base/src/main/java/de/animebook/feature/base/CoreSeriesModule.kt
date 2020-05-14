package de.animebook.feature.base

import de.animebook.core.remote.RetrofitClient
import de.animebook.core.remote.series.mapper.RemoteSeriesMapper
import de.animebook.core.remote.series.network.AnimeSeriesWebServiceProvider
import de.animebook.core.remote.series.repo.SeriesRemoteImpl
import de.animebook.core.series.repo.SeriesRemote
import de.animebook.core.series.repo.SeriesRepo
import de.animebook.core.series.repo.SeriesRepoImpl
import de.animebook.core.series.usecase.FetchAnimeSeriesUseCase
import org.rewedigital.katana.Module
import org.rewedigital.katana.dsl.factory
import org.rewedigital.katana.dsl.get
import org.rewedigital.katana.dsl.singleton

val SeriesModule =
    Module(
        name = "SeriesRemoteModule"
    ) {

        singleton { RetrofitClient() }

        factory { RemoteSeriesMapper() }

        singleton { AnimeSeriesWebServiceProvider.webService }

        factory<SeriesRemote> { SeriesRemoteImpl(get(), get()) }

        factory<SeriesRepo> { SeriesRepoImpl(get()) }

        factory { FetchAnimeSeriesUseCase(get()) }

    }