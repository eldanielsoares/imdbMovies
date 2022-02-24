package com.enjoy.imdbjetpackcompose.data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface SeriesService {
    @GET("trending/movie/week?api_key=5f1691433bb484fe433e96eb8236f954&language=pt-BR&page=5")
    suspend fun getSeries(): Series

}

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://api.themoviedb.org/3/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

val seriesServices: SeriesService = retrofit.create(SeriesService::class.java)