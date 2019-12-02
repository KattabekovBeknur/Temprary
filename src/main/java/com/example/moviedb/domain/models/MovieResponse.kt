package com.example.moviedb.domain.models


data class MovieResponse(
    val page: Int?,
    val totalPages: Int?,
    val totalResults: Int?,
    val results: List<Movie>?
)