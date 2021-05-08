package eu.pbenayoun.thatdmdbapp.repository.remote.retrofit

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query



interface RetrofitPopularMoviesService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = "b01715618805cb81c6d64d70bb6092a5",
        @Query("page") page: Int
    ): Response<RetrofitMoviesResponse>
}