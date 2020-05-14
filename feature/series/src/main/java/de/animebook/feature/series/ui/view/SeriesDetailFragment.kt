package de.animebook.feature.series.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import de.animebook.core.presentation.series.view.SeriesDetailView
import de.feature.series.R

//TODO: detail fragment for detailed data view
class SeriesDetailFragment : Fragment(),
    SeriesDetailView {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.series_detail_fragment, container, false)
    }

}
