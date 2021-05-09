package eu.pbenayoun.thatdmdbapp.repository.database.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.pbenayoun.thatdmdbapp.repository.model.TMDBMovie

@Entity
class MovieEntity(
        @PrimaryKey
        val id: Long,
        val title: String,
        val posterUrl: String,
        val releaseDate: String,
        val userRating:Int=-1) {

        constructor(tmdbMovie: TMDBMovie):this(tmdbMovie.id,tmdbMovie.title,tmdbMovie.posterUrl,tmdbMovie.releaseDate,tmdbMovie.userRating)

        fun mapToTMDBMovie() : TMDBMovie{
                return TMDBMovie(id,title,posterUrl,releaseDate,userRating)
        }
}