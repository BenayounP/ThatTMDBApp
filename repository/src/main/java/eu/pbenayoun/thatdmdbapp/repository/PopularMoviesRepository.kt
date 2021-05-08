package eu.pbenayoun.thatdmdbapp.repository

import androidx.lifecycle.LiveData
import eu.pbenayoun.thatdmdbapp.repository.model.TMDBMovie

interface PopularMoviesRepository {
    val popularMovies: LiveData<List<TMDBMovie>>
    suspend fun updatePopularMovies()
}