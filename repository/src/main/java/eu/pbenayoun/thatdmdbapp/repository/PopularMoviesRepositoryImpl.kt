package eu.pbenayoun.thatdmdbapp.repository

import eu.pbenayoun.thatdmdbapp.repository.remote.retrofit.RetrofitService
import javax.inject.Inject

class PopularMoviesRepositoryImpl @Inject constructor(): PopularMoviesRepository {
    val retrofitService = RetrofitService()

    override fun getPopularMovies() {
        retrofitService.getPopularMovies(1)
    }
}