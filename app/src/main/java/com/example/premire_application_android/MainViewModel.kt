
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel: ViewModel(){
    val movies = MutableStateFlow<TmdbMovieResult>(TmdbMovieResult())
    val series = MutableStateFlow<TmdbSeriesResult>(TmdbSeriesResult())
    val persons = MutableStateFlow<TmdbPersonResult>(TmdbPersonResult())
    val movie = MutableStateFlow<TmdbMovieDetail>(TmdbMovieDetail())
    val serie = MutableStateFlow<TmdbSerieDetail>(TmdbSerieDetail())
    val person = MutableStateFlow<TmdbPersonDetail>(TmdbPersonDetail())
    val personmovie = MutableStateFlow<PersonDetailMovie>(PersonDetailMovie())
    val personserie = MutableStateFlow<PersonDetailSerie>(PersonDetailSerie())

    // Initialisation de l'interface
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();
    val api = retrofit.create(com.example.premire_application_android.api::class.java)

    val api_key = "166e544a3195c0c362b7c9294e90775d"
    fun getFilmInitiaux(){
        viewModelScope.launch {
            movies.value = api.lastmovies(api_key, "fr")
        }
    }
    fun getSeriesInitiaux(){
        viewModelScope.launch {
            series.value = api.lastseries(api_key, "fr")
        }
    }

    fun getPersonsInitiaux(){
        viewModelScope.launch {
            persons.value = api.lastpersons(api_key)
        }
    }

    fun getMovieDetail(movieid: String){
        viewModelScope.launch {
            movie.value = api.moviedetails(movieid, api_key, "fr")
        }
    }

    fun getSerieDetail(serieid: String){
        viewModelScope.launch {
            serie.value = api.seriedetails(serieid, api_key, "fr")
        }
    }

    fun getPersonDetail(personid: String){
        viewModelScope.launch {
            person.value = api.persondetails(personid, api_key, "fr")
        }
    }

    fun getPersonSearch(query: String){
        viewModelScope.launch {
            persons.value = api.personsearch(query, api_key, "fr")
        }
    }

    fun getMovieSearch(query: String){
        viewModelScope.launch {
            movies.value = api.moviesearch(query, api_key, "fr")
        }
    }

    fun getSerieSearch(query: String){
        viewModelScope.launch {
            series.value = api.seriesearch(query, api_key, "fr")
        }
    }

    fun getPersonDetailMovies(personid: String){
        viewModelScope.launch {
            personmovie.value = api.personmoviedetails(personid, api_key, "fr")
        }
    }

    fun getPersonDetailSeries(personid: String){
        viewModelScope.launch {
            personserie.value = api.persontvdetails(personid, api_key, "fr")
        }
    }
}
