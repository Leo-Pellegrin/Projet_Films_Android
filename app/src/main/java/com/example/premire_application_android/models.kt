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

class TmdbSeriesResult(
    var page: Int = 0,
    val results: List<TmdbSerie> = listOf()
)
class TmdbSerie(
    var adult: Boolean = false,
    var backdrop_path: String = "",
    var id: String = "",
    var name: String = "",
    var original_language: String = "",
    var original_name: String = "",
    var overview: String = "",
    var poster_path: String = "",
    var media_type: String = "",
    val genre_ids: List<Int> = listOf(),
    var popularity: String = "",
    var first_air_date: String = "",
    var vote_average: String = "",
    var vote_count: String = "",
    val origin_country: List<String> = listOf()
)

class TmdbPersonResult(
    var page: Int = 0,
    val results: List<TmdbPerson> = listOf()
)
class TmdbPerson(
    var adult: Boolean = false,
    var id: String = "",
    var name: String = "",
    var original_name: String = "",
    var media_type: String = "",
    var popularity: String = "",
    var gender: String = "",
    var known_for_department: String = "",
    var profile_path: String = "",
    var known_for: List<TmdbMovie> = listOf()
)
