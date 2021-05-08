package eu.pbenayoun.thattmdbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import eu.pbenayoun.thatdmdbapp.repository.remote.retrofit.RetrofitService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofitService = RetrofitService()
        retrofitService.getPopularMovies()
    }
}