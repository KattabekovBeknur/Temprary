package com.example.moviedb.data.mappers

import com.example.moviedb.data.models.MovieData
import com.example.moviedb.domain.Mapper
import com.example.moviedb.domain.models.Movie

class MovieMapper: Mapper<MovieData, Movie> {

    override fun from(item: MovieData): Movie {
        return Movie (
            id = item.id,
            title = item.title,
            voteCount = item.voteCount,
            adult = item.adult,
            voteAverage = item.voteAverage,
            overview = item.overview,
            releaseDate = item.releaseDate,
            posterPath = item.posterPath,
            backdropPath = item.backdropPath
        )
    }

    override fun to(item: Movie): MovieData {
        return MovieData(
            id = item.id,
            title = item.title,
            voteCount = item.voteCount,
            adult = item.adult,
            voteAverage = item.voteAverage,
            overview = item.overview,
            releaseDate = item.releaseDate,
            posterPath = item.posterPath,
            backdropPath = item.backdropPath
        )
    }
}