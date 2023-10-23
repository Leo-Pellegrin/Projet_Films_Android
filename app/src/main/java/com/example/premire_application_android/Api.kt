package com.example.premire_application_android

import PersonDetailMovie
import PersonDetailSerie
import TmdbMovieDetail
import TmdbMovieResult
import TmdbPersonDetail
import TmdbPersonResult
import TmdbSerieDetail
import TmdbSeriesResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface api {
    @GET("trending/movie/week")
    suspend fun lastmovies(@Query("api_key") api_key : String, @Query("language") language: String): TmdbMovieResult

    @GET("trending/tv/week")
    suspend fun lastseries(@Query("api_key") api_key: String, @Query("language") language: String): TmdbSeriesResult

    @GET("trending/person/week")
    suspend fun lastpersons(@Query("api_key") api_key: String): TmdbPersonResult

    @GET("movie/{id}?append_to_response=credits")
    suspend fun moviedetails(@Path("id") id: String, @Query("api_key") api_key: String, @Query("language") language: String): TmdbMovieDetail

    @GET("tv/{id}?append_to_response=credits")
    suspend fun seriedetails(@Path("id") id: String, @Query("api_key") api_key: String, @Query("language") language: String): TmdbSerieDetail

    @GET("person/{id}?append_to_response=credits")
    suspend fun persondetails(@Path("id") id: String, @Query("api_key") api_key: String, @Query("language") language: String): TmdbPersonDetail
    @GET("person/{id}/movie_credits?append_to_response=credits")
    suspend fun personmoviedetails(@Path("id") id: String, @Query("api_key") api_key: String, @Query("language") language: String): PersonDetailMovie
    @GET("person/{id}/tv_credits?append_to_response=credits")
    suspend fun persontvdetails(@Path("id") id: String, @Query("api_key") api_key: String, @Query("language") language: String): PersonDetailSerie
    @GET("search/person")
    suspend fun personsearch(@Query("query") query: String, @Query("api_key")api_key: String, @Query("language") language: String): TmdbPersonResult

    @GET("search/movie")
    suspend fun moviesearch(@Query("query") query: String, @Query("api_key")api_key: String, @Query("language") language: String): TmdbMovieResult

    @GET("search/tv")
    suspend fun seriesearch(@Query("query") query: String, @Query("api_key")api_key: String, @Query("language") language: String): TmdbSeriesResult
}

