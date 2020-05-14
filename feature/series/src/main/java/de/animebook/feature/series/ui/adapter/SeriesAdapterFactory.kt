package de.animebook.feature.series.ui.adapter

import de.animebook.core.presentation.series.model.PresentationSelectSeriesEvent
import kotlinx.coroutines.channels.Channel

class SeriesAdapterFactory() {

    fun create(
        onSeriesSelectedChannel: Channel<PresentationSelectSeriesEvent>
    ) =
        SeriesAdapter(onSeriesSelectedChannel = onSeriesSelectedChannel)
}