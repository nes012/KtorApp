package anzhy.dizi.ktorapp.di

import androidx.paging.ExperimentalPagingApi
import anzhy.dizi.ktorapp.data.local.AnimeDatabase
import anzhy.dizi.ktorapp.data.remote.AnimeApi
import anzhy.dizi.ktorapp.data.repository.RemoteDataSource
import anzhy.dizi.ktorapp.data.repository.RemoteDataSourceImpl
import anzhy.dizi.ktorapp.util.Constants.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalPagingApi
@OptIn(ExperimentalSerializationApi::class)
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            //http client will wait for 15 sec when will read from server. after those 15 sec, if we don't
            //receive a response from server then we are going to be able show message
            .readTimeout(15, TimeUnit.MINUTES)
            .connectTimeout(15, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        val contentType = MediaType.get("application/json")
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            //Deserialization. convert json response from the server back into api response object
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideAnimeApi(retrofit: Retrofit): AnimeApi {
        return retrofit.create(AnimeApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRemoteDataSource(
        animeApi: AnimeApi,
        animeDatabase: AnimeDatabase
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            animaApi = animeApi,
            animeDatabase = animeDatabase
        )
    }

}