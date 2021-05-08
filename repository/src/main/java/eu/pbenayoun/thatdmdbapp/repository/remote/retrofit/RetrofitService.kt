package eu.pbenayoun.thatdmdbapp.repository.remote.retrofit

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitService {



    private val retrofitPopularMoviesService: RetrofitPopularMoviesService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofitPopularMoviesService = retrofit.create(RetrofitPopularMoviesService::class.java)
    }

    suspend fun getPopularMovies(page: Int) : List<RetrofitMovie> =  withContext(Dispatchers.Default) {
    val response = retrofitPopularMoviesService.getPopularMovies(page=page)
        if (response.isSuccessful) {
            response.body()?.retrofitMovies?: listOf()
        }
        else listOf()
    }
}