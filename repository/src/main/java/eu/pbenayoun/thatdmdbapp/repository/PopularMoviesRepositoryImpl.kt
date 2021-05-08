package eu.pbenayoun.thatdmdbapp.repository

import androidx.lifecycle.MutableLiveData
import eu.pbenayoun.thatdmdbapp.repository.model.TMDBMovie
import eu.pbenayoun.thatdmdbapp.repository.remote.retrofit.RetrofitService
import javax.inject.Inject

class PopularMoviesRepositoryImpl @Inject constructor(): PopularMoviesRepository {
    val retrofitService = RetrofitService()

    override val popularMovies = MutableLiveData<List<TMDBMovie>>()

    override suspend fun updatePopularMovies(){
        popularMovies.value=retrofitService.getPopularMovies(1).map {retrofitMovie -> retrofitMovie.mapToTMDBMovie()}
    }



}