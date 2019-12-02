package com.example.moviedb.presentation.movie.cinema

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moviedb.R
import kotlinx.android.synthetic.main.cinema_map.*

class FragmentMap:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cinema_map, container, false)
    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        text_map.text = "Fragment Map"
//    }
}