package cl.bootcamp.sprint6.di

import android.content.Context
import androidx.room.Room
import cl.bootcamp.sprint6.dataSource.DbDataSource
import cl.bootcamp.sprint6.dataSource.RestDatoSource
import cl.bootcamp.sprint6.model.ProductDao
import cl.bootcamp.sprint6.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl() = BASE_URL

    @Singleton
    @Provides
    fun providesRetrofit(@Named("BaseUrl") baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Provides
    fun restDataSource(retrofit: Retrofit): RestDatoSource =
        retrofit.create(RestDatoSource::class.java)

    @Singleton
    @Provides
    fun DbDataSource(@ApplicationContext context: Context): DbDataSource {

        return Room.databaseBuilder(context, DbDataSource::class.java, "product_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun productDao(db: DbDataSource): ProductDao = db.productDao()
}