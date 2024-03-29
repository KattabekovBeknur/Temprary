package com.example.moviedb.domain

interface Mapper<M, N> {
    fun from(item: M) : N

    fun to(item:N): M
}