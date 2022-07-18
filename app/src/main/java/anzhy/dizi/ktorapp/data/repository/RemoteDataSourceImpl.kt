package anzhy.dizi.ktorapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import anzhy.dizi.ktorapp.data.local.AnimeDatabase
import anzhy.dizi.ktorapp.data.paging_source.HeroRemoteMediator
import anzhy.dizi.ktorapp.data.remote.AnimeApi
import anzhy.dizi.ktorapp.domain.model.Hero
import anzhy.dizi.ktorapp.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

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

    override fun searchHeroes(): Flow<PagingData<Hero>> {
        TODO()
    }
}