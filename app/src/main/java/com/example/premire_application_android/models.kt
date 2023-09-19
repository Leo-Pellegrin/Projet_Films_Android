class TmdbMovieResult(
    var page: Int = 0,
    val results: List<TmdbMovie> = listOf())

class TmdbMovie(
    var adult: Boolean = false,
    var backdrop_path: String = "",
    var id: String = "",
    var title:String = "",
    var original_language: String = "",
    val original_title: String = "",
    var overview: String = "",
    val poster_path: String = "",
    val media_type: String = "",
    val genre_ids: List<Int> = listOf(),
    val popularity: String = "",
    val release_date: String = "",
    val video: Boolean = false,
    val vote_average: String = "",
    val vote_count: String = "",


)

