package eu.pbenayoun.thatdmdbapp.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import eu.pbenayoun.thatdmdbapp.repository.database.room.BestMovieDataBase
import eu.pbenayoun.thatdmdbapp.repository.database.room.MovieEntity
import eu.pbenayoun.thatdmdbapp.repository.model.TMDBMovie
import eu.pbenayoun.thatdmdbapp.repository.remote.retrofit.RetrofitService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularMoviesRepositoryImpl @Inject constructor(@ApplicationContext context: Context): PopularMoviesRepository {
    val retrofitService = RetrofitService()
    val bestMoviesDao = BestMovieDataBase.getInstance(context).BestMoviesDao()

    override val popularMovies = MutableLiveData<List<TMDBMovie>>()

    override suspend fun updatePopularMovies(){
        val retrofitMovies = retrofitService.getPopularMovies(1)
        if (retrofitMovies.size!=0) {
            retrofitMovies.map {retrofitMovie -> bestMoviesDao.insertMovie(MovieEntity(retrofitMovie.mapToTMDBMovie()))}
        }
        popularMovies.postValue(bestMoviesDao.getAll().map { movieEntity -> movieEntity.mapToTMDBMovie() })
    }

}