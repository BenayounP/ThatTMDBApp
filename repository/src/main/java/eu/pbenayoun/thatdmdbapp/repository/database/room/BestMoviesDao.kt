package eu.pbenayoun.thatdmdbapp.repository.database.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BestMoviesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movieEntity :MovieEntity)

    @Query("select * from MovieEntity order by releaseDate")
    suspend fun getAll() : List<MovieEntity>
}