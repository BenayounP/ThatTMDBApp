package eu.pbenayoun.thatdmdbapp.repository.remote.retrofit

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitService {

    private val api: RetrofitPopularMoviesService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(RetrofitPopularMoviesService::class.java)
    }



    fun getPopularMovies(page: Int) {
        api.getPopularMovies(page = page)
            .enqueue(object : Callback<RetrofitMoviesResponse> {
                override fun onResponse(
                    call: Call<RetrofitMoviesResponse>,
                    response: Response<RetrofitMoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            Log.d("TMP_DEBUG", "Movies: ${responseBody.retrofitMovies}")
                        } else {
                            Log.d("TMP_DEBUG", "Failed to get response")
                        }
                    }
                }

                override fun onFailure(call: Call<RetrofitMoviesResponse>, t: Throwable) {
                    Log.e("TMP_DEBUG", "onFailure", t)
                }
            })
    }
}