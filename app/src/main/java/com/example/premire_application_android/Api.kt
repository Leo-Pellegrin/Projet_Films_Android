package com.example.premire_application_android

import TmdbMovieResult
import retrofit2.http.GET
import retrofit2.http.Query

interface api {
    @GET("trending/movie/week")
    suspend fun lastmovies(@Query("api_key") api_key : String): TmdbMovieResult

}

