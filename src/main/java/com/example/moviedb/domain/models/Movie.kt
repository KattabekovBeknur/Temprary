package com.example.moviedb.domain.models

data class Movie (
    val id: Int?,
    val voteCount: Int?,
    val adult: Boolean?,
    val title: String?,
    val voteAverage: Double?,
    val overview: String?,
    val releaseDate: String?,
    val posterPath: String?,
    val backdropPath: String?
)