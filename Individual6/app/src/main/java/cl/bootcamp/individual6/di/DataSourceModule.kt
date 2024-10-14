package cl.bootcamp.individual6.di

import android.content.Context
import androidx.room.Room
import cl.bootcamp.individual6.datasource.DbDataSource
import cl.bootcamp.individual6.datasource.RestDataSource
import cl.bootcamp.individual6.model.MoviesDao
import cl.bootcamp.individual6.util.Constant.Companion.BASE_URL
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
object DataSourceModule{
    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesRestDataSource(retrofit: Retrofit): RestDataSource {
        return retrofit.create(RestDataSource::class.java)
    }

    @Singleton
    @Provides
    fun bdDataSource(@ApplicationContext context: Context) : DbDataSource {
        return Room.databaseBuilder(context, DbDataSource::class.java, "movies_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesMoviesDao(db: DbDataSource): MoviesDao = db.moviesDao()
}
