package anzhy.dizi.ktorapp.paging_source

import androidx.paging.*
import androidx.test.core.app.ApplicationProvider
import anzhy.dizi.ktorapp.data.local.AnimeDatabase
import anzhy.dizi.ktorapp.data.paging_source.HeroRemoteMediator
import anzhy.dizi.ktorapp.domain.model.Hero
import anzhy.dizi.ktorapp.remote.SecondFakeAnimeApi
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class HeroRemoteMediatorTest {

    private lateinit var animeApi: SecondFakeAnimeApi
    private lateinit var animeDatabase: AnimeDatabase

    @Before
    fun setup() {
        animeApi = SecondFakeAnimeApi()
        animeDatabase = AnimeDatabase.create(
            ApplicationProvider.getApplicationContext(),
            useInMemory = true
        )
    }

    @After
    fun cleanup() {
        animeDatabase.clearAllTables()
    }

    @ExperimentalPagingApi
    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() =
        runBlocking {
            val remoteMediator = HeroRemoteMediator(
                animeApi = animeApi,
                animeDatabase = animeDatabase
            )
            val pagingState = PagingState<Int, Hero>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )
            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is RemoteMediator.MediatorResult.Success)
            assertFalse((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
        }

    @ExperimentalPagingApi
    @Test
    fun refreshLoadSuccessAndEndOfPaginationTrueWhenNoMoreData() =
        runBlocking {
            animeApi.clearData()
            val remoteMediator = HeroRemoteMediator(
                animeApi = animeApi,
                animeDatabase = animeDatabase
            )
            val pagingState = PagingState<Int, Hero>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )
            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is RemoteMediator.MediatorResult.Success)
            assertTrue((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
        }
}
