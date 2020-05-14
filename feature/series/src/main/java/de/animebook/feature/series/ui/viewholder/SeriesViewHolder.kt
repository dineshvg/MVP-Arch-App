package de.animebook.feature.series.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import de.animebook.core.presentation.series.model.PresentationSelectSeriesEvent
import de.animebook.core.presentation.series.model.PresentationSeries
import kotlinx.android.synthetic.main.series_list_item_row.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class SeriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        seriesToInflate: PresentationSeries,
        onSeriesSelectedChannel: Channel<PresentationSelectSeriesEvent>
    ) {
        with(itemView) {
            print("Item id ${seriesToInflate.id}")
            series_row_name.text = seriesToInflate.name
            series_row_short_desc.text = seriesToInflate.shortDescription
            series_row_episode_count_value.text = seriesToInflate.episodes.toString()
            series_row_small_image.setImageURI(seriesToInflate.smallImage)
            setOnClickListener {
                GlobalScope.launch {
                    onSeriesSelectedChannel.send(
                        PresentationSelectSeriesEvent(
                            presentationSeries = seriesToInflate
                        )
                    )
                }

            }
        }
    }
}