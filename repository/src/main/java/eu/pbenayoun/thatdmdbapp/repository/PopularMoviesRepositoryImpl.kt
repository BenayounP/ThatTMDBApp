package eu.pbenayoun.thatdmdbapp.repository

import android.content.Context
import android.util.Log
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

    override suspend fun getPopularMovies(){
        val retrofitMovies = retrofitService.getPopularMovies(1)
        if (retrofitMovies.size!=0) {
            retrofitMovies.map {retrofitMovie -> bestMoviesDao.insertMovie(MovieEntity(retrofitMovie.mapToTMDBMovie()))}
        }
        popularMovies.postValue(bestMoviesDao.getAll().map { movieEntity -> movieEntity.mapToTMDBMovie() })
    }

    override suspend fun updateMovie(tmdbMovie: TMDBMovie){
        Log.d("TMP_DEBUG", "update movie: ${tmdbMovie.title}: ${tmdbMovie.userRating}")
        bestMoviesDao.update(MovieEntity(tmdbMovie))
    }
}