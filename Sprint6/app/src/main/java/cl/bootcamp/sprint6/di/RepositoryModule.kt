package cl.bootcamp.sprint6.di

import cl.bootcamp.sprint6.repository.ProductsRepository
import cl.bootcamp.sprint6.repository.ProductsRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun productRepository(repositoryImp: ProductsRepositoryImp): ProductsRepository
}