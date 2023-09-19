import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class FilmViewModel: ViewModel(){
    val movies = MutableStateFlow<TmdbMovieResult>(TmdbMovieResult())
    //val image = MutableStateFlow()

    // Initialisation de l'interface
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();
    val api = retrofit.create(com.example.premire_application_android.api::class.java)

    fun getFilmInitiaux(){
        viewModelScope.launch {
            movies.value = api.lastmovies("166e544a3195c0c362b7c9294e90775d")
        }
    }


}

class ImageViewModel: ViewModel(){
    val image = MutableStateFlow<ResponseBody?>(null)

    val retrofit = Retrofit.Builder()
        .baseUrl("https://image.tmdb.org/t/p/w780/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();
    val api = retrofit.create(com.example.premire_application_android.api::class.java)

    fun getImage(backdrop_path: String){
        viewModelScope.launch{
            image.value = api.image("166e544a3195c0c362b7c9294e90775d", backdrop_path)
        }
    }
}