package eu.pbenayoun.thatdmdbapp.repository.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class BestMovieDataBase : RoomDatabase() {
    abstract fun BestMoviesDao() : BestMoviesDao

    companion object {
        // For Singleton instantiation
        @Volatile private var instance: BestMovieDataBase? = null

        fun getInstance(context: Context): BestMovieDataBase {
            return instance ?: synchronized(this) {
                instance
                        ?: buildDatabase(
                                context
                        ).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): BestMovieDataBase {
            return Room.databaseBuilder(context, BestMovieDataBase::class.java, DATABASE_NAME).build()
        }
    }
}

private const val DATABASE_NAME = "bestMovies-db-1"