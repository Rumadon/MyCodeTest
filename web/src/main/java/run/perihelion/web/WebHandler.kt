package run.perihelion.web

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import run.perihelion.models.Gist


object WebHandler {
    const val BASE_URL = "https://api.github.com/gists"
    private val moshi by lazy {
        Moshi.Builder()
            // ... add your own JsonAdapters and factories ...
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
//            .addCallAdapterFactory(moshi)
            .baseUrl("https://api.github.com/")
            .build()
    }

    private var service = retrofit.create(GitHubService::class.java)

    suspend fun getGists(): List<Gist> {
        return service.listGists()
    }

    interface GitHubService {
        @GET("gists")
        suspend fun listGists(): List<Gist>
    }
}