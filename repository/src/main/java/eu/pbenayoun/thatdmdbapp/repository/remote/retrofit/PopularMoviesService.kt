package eu.pbenayoun.thatdmdbapp.repository.remote.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query



interface PopularMoviesService {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "b01715618805cb81c6d64d70bb6092a5",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>
}