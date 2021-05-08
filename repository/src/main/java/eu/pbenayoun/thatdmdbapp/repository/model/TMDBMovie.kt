package eu.pbenayoun.thatdmdbapp.repository.model


data class TMDBMovie(
    val id: Long,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val userRating:Int=-1)
