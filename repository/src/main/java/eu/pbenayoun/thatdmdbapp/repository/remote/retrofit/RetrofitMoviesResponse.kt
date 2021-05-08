package eu.pbenayoun.thatdmdbapp.repository.remote.retrofit

import com.google.gson.annotations.SerializedName

data class RetrofitMoviesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val retrofitMovies: List<RetrofitMovie>,
    @SerializedName("total_pages") val pages: Int
)