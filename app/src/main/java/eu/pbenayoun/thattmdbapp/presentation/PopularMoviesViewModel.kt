package eu.pbenayoun.thattmdbapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.pbenayoun.thatdmdbapp.repository.PopularMoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor
    (private val popularMoviesRepository: PopularMoviesRepository) : ViewModel() {

    val popularMovies = popularMoviesRepository.popularMovies

    fun updatePopularMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            popularMoviesRepository.updatePopularMovies()
        }
    }
}