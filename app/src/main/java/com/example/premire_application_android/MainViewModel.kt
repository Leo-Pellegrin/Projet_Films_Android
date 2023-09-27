
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

    // Initialisation de l'interface
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();
    val api = retrofit.create(com.example.premire_application_android.api::class.java)

    fun getFilmInitiaux(){
        viewModelScope.launch {
            movies.value = api.lastmovies("166e544a3195c0c362b7c9294e90775d", "fr")
        }
    }
    fun getSeriesInitiaux(){
        viewModelScope.launch {
            series.value = api.lastseries("166e544a3195c0c362b7c9294e90775d", "fr")
        }
    }

    fun getPersonsInitiaux(){
        viewModelScope.launch {
            persons.value = api.lastpersons("166e544a3195c0c362b7c9294e90775d")
        }
    }

    fun getMovieDetail(movieid: String){
        viewModelScope.launch {
            movie.value = api.moviedetails(movieid, "166e544a3195c0c362b7c9294e90775d", "fr")
        }
    }

    fun getSerieDetail(serieid: String){
        viewModelScope.launch {
            serie.value = api.seriedetails(serieid, "166e544a3195c0c362b7c9294e90775d", "fr")
        }
    }

    fun getPersonDetail(personid: String){
        viewModelScope.launch {
            person.value = api.persondetails(personid, "166e544a3195c0c362b7c9294e90775d", "fr")
        }
    }

    fun getPersonSearch(query: String){
        viewModelScope.launch {
            persons.value = api.personsearch(query, "166e544a3195c0c362b7c9294e90775d", "fr")
        }
    }

    fun getMovieSearch(query: String){
        viewModelScope.launch {
            movies.value = api.moviesearch(query, "166e544a3195c0c362b7c9294e90775d", "fr")
        }
    }

    fun getSerieSearch(query: String){
        viewModelScope.launch {
            series.value = api.seriesearch(query, "166e544a3195c0c362b7c9294e90775d", "fr")
        }
    }
}
