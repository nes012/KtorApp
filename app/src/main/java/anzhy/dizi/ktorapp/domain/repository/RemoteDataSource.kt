package anzhy.dizi.ktorapp.domain.repository

import androidx.paging.PagingData
import anzhy.dizi.ktorapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllHeroes(): Flow<PagingData<Hero>>
    fun searchHeroes(query: String): Flow<PagingData<Hero>>
}