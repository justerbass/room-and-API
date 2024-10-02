package cl.bootcamp.integrador1.di

import cl.bootcamp.integrador1.repository.UserRepository
import cl.bootcamp.integrador1.repository.UserRepositoryImp
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
    abstract fun userRepository(repositoryImp: UserRepositoryImp): UserRepository
}
