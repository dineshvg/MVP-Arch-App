package de.animebook.di

import android.app.Application
import android.content.Context
import de.animebook.feature.base.Names
import de.animebook.feature.base.SeriesModule
import org.rewedigital.katana.Module
import org.rewedigital.katana.android.modules.ApplicationModule
import org.rewedigital.katana.dsl.singleton

typealias ContextProvider = () -> Context

fun ApplicationModule(app: Application) = Module(
    name = "AnimeBookAppModule",
    includes = listOf(
        ApplicationModule(app),
        SeriesModule
    )
) {
    singleton<ContextProvider>(Names.APPLICATION_CONTEXT_PROVIDER) { { app.applicationContext } }
}