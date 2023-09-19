package com.example.premire_application_android

import TmdbMovieResult
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface api {
    @GET("trending/movie/week")
    suspend fun lastmovies(@Query("api_key") api_key : String): TmdbMovieResult

    @GET("{id}")
    suspend fun image(@Path("backdrop_path") backdrop_path: String): ResponseBody
}

