package eu.pbenayoun.thattmdbapp.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.pbenayoun.thatdmdbapp.repository.PopularMoviesRepository
import javax.inject.Inject

@HiltViewModel
class PopularMoviesRepositoryViewModel @Inject constructor
    (private val popularMoviesRepository: PopularMoviesRepository) : ViewModel() {

    fun getPopularMovies(){
        popularMoviesRepository.getPopularMovies()
    }
}