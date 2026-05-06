import com.example.joelsanchez_pokeapi.remote.PokemonApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // URL base de la PokeAPI, a partir de ella se construyen todos los endpoints
    private const val BASE_URL = "https://pokeapi.co/api/v2/"
    private var retrofit: Retrofit? = null

    val instance: Retrofit
        // Inicializados de la instancia
        get() {
            if (retrofit == null) {
                // Inicializamos la instancia con la URL base
                // y la librería que mapea JSON a Java (Gson Converter)
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }

    val pokemonApi: PokemonApi
        // Inicializamos la interfaz, conectando con el cliente creado anteriormente
        get() = instance.create<PokemonApi?>(PokemonApi::class.java)
}