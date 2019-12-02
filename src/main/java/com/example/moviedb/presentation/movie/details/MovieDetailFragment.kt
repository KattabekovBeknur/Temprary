package com.example.moviedb.presentation.movie.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide

import com.example.moviedb.R
import com.example.moviedb.base.BaseFragment
import com.example.moviedb.data.models.MovieData
import com.example.moviedb.data.repository.MovieRepositoryImpl
import com.example.moviedb.domain.repository.MovieRepository
import com.example.moviedb.presentation.movie.favorite.FavoriteMoviesViewModel
import com.example.moviedb.utils.AppConstants
import com.example.moviedb.utils.AppPreferences

class MovieDetailFragment : BaseFragment() {

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var progressBar: ProgressBar
    private lateinit var ivBackdrop: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvRating: TextView
    private lateinit var tvGenre: TextView
    private lateinit var tvOverview: TextView
    private lateinit var btnFavorite: Button
    private var movieId: Int? = 0
    private var status:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)
        bindViews(view)
        setData()

        val accountId = AppPreferences.getAccountId(activity?.applicationContext!!)
        val sessionId = AppPreferences.getSessionId(activity?.applicationContext!!)

        setFavoriteFunc(true,2)

        btnFavorite.setOnClickListener{
            setFavoriteFunc(true,1)
            btnFavorite.setBackgroundResource(R.drawable.button_background)
        }
    }

    private fun setFavoriteFunc(status:Boolean,which:Int){
        val accountId = AppPreferences.getAccountId(activity?.applicationContext!!)
        val sessionId = AppPreferences.getSessionId(activity?.applicationContext!!)
        movieId?.let {
            accountId?.let {
                sessionId?.let {
                    if (which == 1){
                        viewModel.setFavorite(accountId, movieId!!, sessionId,status)
                    }else{
                        viewModel.getMovieStatus(accountId, movieId!!, sessionId,status)
                    }
                }
            }
        }
    }

    override fun bindViews(view: View) = with(view){
        progressBar = view.findViewById(R.id.progressBar)
        ivBackdrop = view.findViewById(R.id.ivBackdrop)
        tvName = view.findViewById(R.id.tvName)
        tvRating = view.findViewById(R.id.tvRating)
        tvGenre = view.findViewById(R.id.tvGenre)
        tvOverview = view.findViewById(R.id.tvOverview)
        btnFavorite = view.findViewById(R.id.btnFavorite)
        movieId = arguments?.getInt(AppConstants.MOVIE_ID)
    }

    override fun setData() {
        movieId?.let { movieId ->
            viewModel.getMovie(movieId)
        }

        viewModel.liveData.observe(viewLifecycleOwner, Observer {result ->
            when(result) {
                is MovieDetailViewModel.State.ShowLoading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is MovieDetailViewModel.State.HideLoading -> {
                    progressBar.visibility = View.GONE
                }
                is MovieDetailViewModel.State.Result -> {
                    val imageUrl = "${AppConstants.BACKDROP_BASE_URL}${result.movie.backdropPath}"
                    Glide.with(this)
                        .load(imageUrl)
                        .into(ivBackdrop)
                    tvName.text = result.movie.title
                    tvRating.text = "${result.movie.voteAverage}/10"
                    tvGenre.text = result.movie.genres?.first()?.name
                    tvOverview.text = result.movie.overview
                }
                is MovieDetailViewModel.State.FavoriteMovie -> {
                    if (result.resultCode == 1) {
                        Toast.makeText(context, "Successfully added to your favorite movies!", Toast.LENGTH_SHORT).show()
                    } else if (result.resultCode == 12) {
                        setFavoriteFunc(false,2)
//                        Toast.makeText(context, "It is already in your favorite", Toast.LENGTH_SHORT).show()
                    }
//                    else if (result.resultCode == 13){
//                        Toast.makeText(context, "Successfully removed from favorite", Toast.LENGTH_SHORT).show()
//                    }
//                    else {
//                        Toast.makeText(context, "Error !! ResultCode is"+result.resultCode.toString(), Toast.LENGTH_SHORT).show()
//                    }
                }
                is MovieDetailViewModel.State.MovieState -> {
                    if (result.resultCode == 1) {
                        setFavoriteFunc(false,2)
                    } else if (result.resultCode == 12) {
                        btnFavorite.setBackgroundResource(R.drawable.button_background)
                    } else if (result.resultCode == 13){
                        btnFavorite.setBackgroundResource(R.drawable.button_background_anim)
                    }
                }
                is MovieDetailViewModel.State.Error -> {
                    Toast.makeText(context, result.error, Toast.LENGTH_SHORT).show()
                }
                is MovieDetailViewModel.State.IntError -> {
                    Toast.makeText(context, result.error, Toast.LENGTH_SHORT).show()
                }
            }
        })

    }
}
