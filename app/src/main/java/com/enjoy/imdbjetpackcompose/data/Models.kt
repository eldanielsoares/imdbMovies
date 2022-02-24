package com.enjoy.imdbjetpackcompose.data

import com.squareup.moshi.Json

data class Series(
    @field:Json(name= "results")
    val result: List<SeriesDetails>
)

data class SeriesDetails(
    @field:Json(name= "title")
    val title: String,
    @field:Json(name= "overview")
    val overview: String,
    @field:Json(name= "poster_path")
    val poster_path: String,
    @field:Json(name = "vote_average")
    val vote_average : String
)
