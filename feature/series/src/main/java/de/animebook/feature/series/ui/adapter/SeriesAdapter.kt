package de.animebook.feature.series.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import de.animebook.core.presentation.series.model.PresentationSelectSeriesEvent
import de.animebook.core.presentation.series.model.PresentationSeries
import de.animebook.feature.base.extensions.diffUtilItemCallback
import de.animebook.feature.series.ui.viewholder.SeriesViewHolder
import de.feature.series.R
import kotlinx.coroutines.channels.Channel

class SeriesAdapter(
    private val onSeriesSelectedChannel: Channel<PresentationSelectSeriesEvent>
) : ListAdapter<PresentationSeries, SeriesViewHolder>(DIFF_ITEM_CALLBACK) {

    init {
        setHasStableIds(true)
    }

    var items: List<PresentationSeries>
        set(value) = submitList(value)
        get() = currentList

    override fun getItemCount() = items.size

    override fun getItemId(position: Int) = items[position].id.hashCode().toLong()

    private companion object {
        private val DIFF_ITEM_CALLBACK =
            diffUtilItemCallback<PresentationSeries> { oldItem, newItem -> oldItem.id == newItem.id }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SeriesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.series_list_item_row,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        val seriesToInflate = getItem(position)
        holder.bind(seriesToInflate, onSeriesSelectedChannel)
    }

}

