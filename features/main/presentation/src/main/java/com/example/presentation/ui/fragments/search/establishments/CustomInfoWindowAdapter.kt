package com.example.presentation.ui.fragments.search.establishments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.presentation.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class CustomInfoWindowAdapter(context: Context) : GoogleMap.InfoWindowAdapter {

    private val mWindow: View =
        LayoutInflater.from(context).inflate(R.layout.custom_maps_info_window, null)

    private fun renderWindowText(marker: Marker, view: View) {
        val title = marker.title
        val titleView: TextView = view.findViewById(R.id.title)
        if (!title.isNullOrEmpty()) {
            titleView.text = title
        }

        val snippet = marker.snippet
        val snippetView: TextView = view.findViewById(R.id.snippet)
        if (!snippet.isNullOrEmpty()) {
            snippetView.text = snippet
        }
    }

    override fun getInfoWindow(marker: Marker): View {
        renderWindowText(marker, mWindow)
        return mWindow
    }

    override fun getInfoContents(marker: Marker): View {
        renderWindowText(marker, mWindow)
        return mWindow
    }
}