package cl.bootcamp.wallet.di

import cl.bootcamp.wallet.repository.WalletRepository
import cl.bootcamp.wallet.repository.WalletRepositoryImp
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
    abstract fun bindWalletRepository(impl: WalletRepositoryImp): WalletRepository

}