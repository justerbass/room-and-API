package cl.bootcamp.wallet.di

import android.content.Context
import androidx.room.Room
import cl.bootcamp.wallet.datasource.BdDataSource
import cl.bootcamp.wallet.datasource.RestDataSource
import cl.bootcamp.wallet.model.UserDao
import cl.bootcamp.wallet.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class DataSourceModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesRestDataSource(retrofit: Retrofit): RestDataSource =
        retrofit.create(RestDataSource::class.java)

    @Singleton
    @Provides
    fun dbDataSource(@ApplicationContext context: Context): BdDataSource {
        return Room.databaseBuilder(context, BdDataSource::class.java, "user_database")
            .fallbackToDestructiveMigration()
            .build()

    }

    @Singleton
    @Provides
    fun userDao(db: BdDataSource): UserDao = db.userDao()


}