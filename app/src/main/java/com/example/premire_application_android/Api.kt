package com.example.premire_application_android

import TmdbMovieResult
import TmdbPersonResult
import TmdbSeriesResult
import retrofit2.http.GET
import retrofit2.http.Query

interface api {
    @GET("trending/movie/week")
    suspend fun lastmovies(@Query("api_key") api_key : String): TmdbMovieResult

    @GET("trending/tv/week")
    suspend fun lastseries(@Query("api_key") api_key: String): TmdbSeriesResult

    @GET("trending/person/week")
    suspend fun lastpersons(@Query("api_key") api_key: String): TmdbPersonResult
}

