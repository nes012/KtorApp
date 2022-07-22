package anzhy.dizi.ktorapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import anzhy.dizi.ktorapp.data.local.AnimeDatabase
import anzhy.dizi.ktorapp.data.paging_source.HeroRemoteMediator
import anzhy.dizi.ktorapp.data.paging_source.SearchHeroesSource
import anzhy.dizi.ktorapp.data.remote.AnimeApi
import anzhy.dizi.ktorapp.domain.model.Hero
import anzhy.dizi.ktorapp.domain.repository.RemoteDataSource
import anzhy.dizi.ktorapp.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val animaApi: AnimeApi,
    private val animeDatabase: AnimeDatabase
) : RemoteDataSource {

    private val heroDao = animeDatabase.heroDao()

    override fun getAllHeroes(): Flow<PagingData<Hero>> {
        val pagingSourceFactory = { heroDao.getAllHeroes() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = HeroRemoteMediator(
                animeApi = animaApi,
                animeDatabase = animeDatabase
            ),
            pagingSourceFactory = pagingSourceFactory,
        ).flow
    }

    override fun searchHeroes(query: String): Flow<PagingData<Hero>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchHeroesSource(
                    animeApi = animaApi,
                    query = query
                )
            }
        ).flow
    }
}