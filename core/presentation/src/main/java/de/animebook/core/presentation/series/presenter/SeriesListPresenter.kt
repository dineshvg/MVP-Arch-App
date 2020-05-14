package de.animebook.core.presentation.series.presenter

import de.animebook.core.presentation.series.interactor.SeriesListInteractor
import de.animebook.core.presentation.series.model.PresentationSelectSeriesEvent
import de.animebook.core.presentation.series.view.SeriesListView
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach

interface SeriesListPresenter {

    val onSeriesSelectedChannel: Channel<PresentationSelectSeriesEvent>

    fun getView(view: SeriesListView)

    fun onCreate()

    fun onStart()

    fun onDestroy()

    fun onFailureOrError()

}

class SeriesListPresenterImpl(
    private val interactor: SeriesListInteractor
) : SeriesListPresenter {

    override val onSeriesSelectedChannel = Channel<PresentationSelectSeriesEvent>()

    private lateinit var view: SeriesListView

    private val backgroundScope: CoroutineScope
        get() = CoroutineScope(Dispatchers.Default + SupervisorJob())

    private val uiScope: CoroutineScope
        get() = CoroutineScope(Dispatchers.Main)

    override fun getView(view: SeriesListView) {
        this.view = view
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate() {
        backgroundScope.async {
            onSeriesSelectedChannel.consumeEach { event ->
                //TODO: detail screen
                //navigator.navigateToDetail(event.presentationSeries)
            }
        }
        uiScope.async {
            view.showLoadingIndicator(visibility = true)
        }
    }

    override fun onStart() {
        backgroundScope.async {
            interactor.subscribeToSeries().let { presentationSeries ->
                uiScope.async {
                    view.showLoadingIndicator(false)
                    when (presentationSeries.size) {
                        0 -> view.showErrorScreen()
                        else -> view.insertData(presentationSeries)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        backgroundScope.cancel()
        uiScope.cancel()
    }

    override fun onFailureOrError() {
        uiScope.async {
            view.showErrorScreen()
        }
    }

}