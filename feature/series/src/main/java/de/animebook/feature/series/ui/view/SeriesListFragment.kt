package de.animebook.feature.series.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import de.animebook.core.presentation.series.model.PresentationSeries
import de.animebook.core.presentation.series.presenter.SeriesListPresenter
import de.animebook.core.presentation.series.view.SeriesListView
import de.animebook.feature.base.SeriesModule
import de.animebook.feature.series.di.SeriesPresentationModule
import de.animebook.feature.series.ui.adapter.SeriesAdapter
import de.animebook.feature.series.ui.adapter.SeriesAdapterFactory
import de.feature.series.R
import kotlinx.android.synthetic.main.series_list_fragment.view.*
import org.rewedigital.katana.Component
import org.rewedigital.katana.KatanaTrait
import org.rewedigital.katana.inject

/**
 * A simple [Fragment] subclass.
 */
class SeriesListFragment : Fragment(),
    SeriesListView, KatanaTrait {

    private val presenter: SeriesListPresenter by inject()

    private val adapterFactory: SeriesAdapterFactory by inject()

    private val seriesAdapter
        get() = view?.anime_series_recycler_view?.adapter as SeriesAdapter?

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.series_list_fragment, container, false).apply {
        presenter.getView(this@SeriesListFragment)
        with(this.anime_series_recycler_view) {
            layoutManager = LinearLayoutManager(context)
            adapterFactory.create(presenter.onSeriesSelectedChannel)
                .let { seriesAdapter ->
                    adapter = seriesAdapter
                }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onCreate()
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override val component: Component
        get() = Component.Builder()
            .addModule(SeriesModule)
            .addModule(SeriesPresentationModule)
            .build()


    override fun insertData(list: List<PresentationSeries>) {
        seriesAdapter?.items = list
        seriesAdapter?.notifyDataSetChanged()
    }

    override fun showErrorScreen() {
        view?.anime_series_error_view?.visibility = View.VISIBLE
        view?.anime_series_recycler_view?.visibility = View.GONE
    }

    override fun showLoadingIndicator(visibility: Boolean) {
        view?.anime_series_loading_indicator?.visibility =
            if (visibility) View.VISIBLE else View.GONE
    }
}



