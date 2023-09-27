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

class TmdbMovieDetail(
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val belongs_to_collection: Any = "",
    val budget: Int = 0,
    val genres: List<Genre> = listOf(),
    val homepage: String = "",
    val id: Int = 0,
    val imdb_id: String = "",
    val original_language: String = "",
    val original_title: String= "",
    val overview: String= "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val production_companies: List<ProductionCompany> = listOf(),
    val production_countries: List<ProductionCountry> = listOf(),
    val release_date: String = "",
    val revenue: Int = 0,
    val runtime: Int = 0,
    val spoken_languages: List<SpokenLanguage> = listOf(),
    val status: String = "",
    val tagline: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Double = 0.0,
    val vote_count: Int = 0,
    val credits: Credits = Credits()
)

data class Credits(
    val cast: List<Cast> = listOf(),
    val crew: List<Crew> = listOf()
)

data class Genre(
    val id: Int,
    val name: String
)

data class ProductionCompany(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
)

data class ProductionCountry(
    val iso_3166_1: String,
    val name: String
)

data class SpokenLanguage(
    val english_name: String,
    val iso_639_1: String,
    val name: String
)

data class Cast(
    val adult: Boolean,
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val order: Int,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)

data class Crew(
    val adult: Boolean,
    val credit_id: String,
    val department: String,
    val gender: Int,
    val id: Int,
    val job: String,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)

data class TmdbSerieDetail(
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val created_by: List<CreatedBy> = listOf(),
    val credits: Credits = Credits(),
    val episode_run_time: List<Any> = listOf(),
    val first_air_date: String = "",
    val genres: List<Genre> = listOf(),
    val homepage: String = "",
    val id: Int = 0,
    val in_production: Boolean = false,
    val languages: List<String> = listOf(),
    val last_air_date: String = "",
    val last_episode_to_air: LastEpisodeToAir = LastEpisodeToAir(),
    val name: String = "",
    val networks: List<Network> = listOf(),
    val next_episode_to_air: NextEpisodeToAir = NextEpisodeToAir(),
    val number_of_episodes: Int = 0,
    val number_of_seasons: Int = 0,
    val origin_country: List<String> = listOf(),
    val original_language: String = "",
    val original_name: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val production_companies: List<ProductionCompany> = listOf(),
    val production_countries: List<ProductionCountry> = listOf(),
    val seasons: List<Season> = listOf(),
    val spoken_languages: List<SpokenLanguage> = listOf(),
    val status: String = "",
    val tagline: String = "",
    val type: String = "",
    val vote_average: Double = 0.0,
    val vote_count: Int = 0
)

data class CreatedBy(
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val name: String,
    val profile_path: String
)

data class LastEpisodeToAir(
    val air_date: String = "",
    val episode_number: Int = 0,
    val episode_type: String = "",
    val id: Int = 0,
    val name: String = "",
    val overview: String = "",
    val production_code: String = "",
    val runtime: Int = 0,
    val season_number: Int = 0,
    val show_id: Int = 0,
    val still_path: String = "",
    val vote_average: Double = 0.0,
    val vote_count: Int = 0
)

data class Network(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
)

data class NextEpisodeToAir(
    val air_date: String = "",
    val episode_number: Int = 0,
    val episode_type: String = "",
    val id: Int = 0,
    val name: String = "",
    val overview: String = "",
    val production_code: String = "",
    val runtime: Any = Any(),
    val season_number: Int = 0,
    val show_id: Int = 0,
    val still_path: String = "",
    val vote_average: Double = 0.0,
    val vote_count: Int = 0
)

data class Season(
    val air_date: String,
    val episode_count: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: String,
    val season_number: Int,
    val vote_average: Double
)

data class TmdbPersonDetail(
    val adult: Boolean = false,
    val also_known_as: List<String> = listOf(),
    val biography: String = "",
    val birthday: String = "",
    val deathday: Any = Any(),
    val gender: Int = 0,
    val homepage: Any = Any(),
    val id: Int = 0,
    val imdb_id: String = "",
    val known_for_department: String = "",
    val name: String = "",
    val place_of_birth: String = "",
    val popularity: Double = 0.0,
    val profile_path: String = ""
)