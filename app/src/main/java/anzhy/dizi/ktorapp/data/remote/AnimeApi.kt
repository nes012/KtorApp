package anzhy.dizi.ktorapp.data.remote

import anzhy.dizi.ktorapp.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeApi {

    @GET("/anime/heroes")
    suspend fun getAllHeroes(
        @Query("page") page: Int = 1
    ): ApiResponse

    @GET("/anime/heroes/search")
    suspend fun searchHeroes(
        @Query("name") name: String
    ): ApiResponse

}