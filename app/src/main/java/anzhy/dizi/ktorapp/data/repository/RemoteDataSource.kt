package anzhy.dizi.ktorapp.data.repository

import androidx.paging.PagingData
import anzhy.dizi.ktorapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllHeroes(): Flow<PagingData<Hero>>
    fun searchHeroes(): Flow<PagingData<Hero>>
}