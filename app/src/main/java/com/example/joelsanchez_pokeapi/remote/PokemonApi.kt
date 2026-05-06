import com.example.joelsanchez_pokeapi.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {

    @GET("pokemon/{name}")
    fun getPokemonByName(@Path("name") name: String?): Call<Pokemon?>?

}