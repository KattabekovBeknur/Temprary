package com.example.moviedb.presentation.movie.cinema

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moviedb.R
import kotlinx.android.synthetic.main.cinema_list.*

class FragmentList: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cinema_list, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        text_list.text = "Fragment List"
    }
}