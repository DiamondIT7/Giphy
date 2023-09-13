package com.example.giphy.model

data class Gif(
    val data: List<GifData>,
    val meta: Meta,
    val pagination: Pagination
)