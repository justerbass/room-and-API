package cl.bootcamp.individual6.di

import cl.bootcamp.individual6.repository.MoviesRepository
import cl.bootcamp.individual6.repository.MoviesRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{
    @Singleton
    @Binds
    abstract fun MoviesRepository(repositoryImpl: MoviesRepositoryImp): MoviesRepository
}