package cl.bootcamp.individual3.di


import cl.bootcamp.individual3.data.ApiNews
import cl.bootcamp.individual3.util.Constant.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsModule {

    //siguiendo hilt
    @Singleton
    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl() = BASE_URL

    @Singleton
    @Provides
    fun providesRetrofit(@Named("BaseUrl") baseUrl: String): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    //eliminamos el retorno y simplemente igualamos
    fun providesNewsApi(retrofit: Retrofit): ApiNews =
        retrofit.create(ApiNews::class.java)

}