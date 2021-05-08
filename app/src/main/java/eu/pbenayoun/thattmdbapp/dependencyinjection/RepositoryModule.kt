package eu.pbenayoun.basa.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import eu.pbenayoun.thatdmdbapp.repository.PopularMoviesRepository
import eu.pbenayoun.thatdmdbapp.repository.PopularMoviesRepositoryImpl


@InstallIn(ViewModelComponent::class)
@Module
abstract
class RepositoryModule {
    @Binds
    abstract fun bindPopularMoviesRepository(impl: PopularMoviesRepositoryImpl): PopularMoviesRepository
}

