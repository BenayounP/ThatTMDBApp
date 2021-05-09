package eu.pbenayoun.thatdmdbapp.repository.database.room

import androidx.room.*

@Dao
interface BestMoviesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movieEntity :MovieEntity)

    @Query("select * from MovieEntity order by releaseDate")
    suspend fun getAll() : List<MovieEntity>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(movieEntity :MovieEntity)
}