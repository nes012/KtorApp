package anzhy.dizi.ktorapp.data.paging_source

import androidx.paging.PagingSource
import anzhy.dizi.ktorapp.data.remote.AnimeApi
import anzhy.dizi.ktorapp.data.remote.FakeAnimeApi
import anzhy.dizi.ktorapp.domain.model.Hero
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

// class will contain 4 different cases
class SearchHeroesSourceTest {

    private lateinit var animeApi: AnimeApi
    private lateinit var heroes: List<Hero>

    //this fun will be executed before each case we will write here
    @Before
    fun setup() {
        animeApi = FakeAnimeApi()
        heroes = listOf(
            Hero(
                id = 1,
                name = "Sasuke",
                image = "",
                about = "",
                rating = 5.0,
                power = 0,
                month = "",
                day = "",
                family = listOf(),
                abilities = listOf(),
                natureTypes = listOf()
            ),
            Hero(
                id = 2,
                name = "Naruto",
                image = "",
                about = "",
                rating = 5.0,
                power = 0,
                month = "",
                day = "",
                family = listOf(),
                abilities = listOf(),
                natureTypes = listOf()
            ),
            Hero(
                id = 3,
                name = "Sakura",
                image = "",
                about = "",
                rating = 4.5,
                power = 0,
                month = "",
                day = "",
                family = listOf(),
                abilities = listOf(),
                natureTypes = listOf()
            )
        )
    }

    // fun will test paging source
    @Test
    fun `Search api with existing hero name, expect single hero result, assert LoadingResult_Page`() {
        // because paging source contain load fun, which is suspend, so that to run suspend fun we need to use runBlockingTest,
        // allow us execute testBody inside an immediate execution dispatcher
        runBlocking {
            val heroSource = SearchHeroesSource(
                animeApi = animeApi,
                // this way we will receive only single hero as a result
                query = "Sasuke"
            )
            assertEquals<PagingSource.LoadResult<Int, Hero>>(
                //we need to confirm that the same class will be returned
                //when response is successful
                expected = PagingSource.LoadResult.Page(
                    data = listOf(heroes.first()),
                    prevKey = null,
                    nextKey = null
                ),
                actual = heroSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )
        }
    }

    // test multiple results
    @Test
    fun `Search api with existing hero name, expect multiple hero result, assert LoadingResult_Page`() {
        // because paging source contain load fun, which is suspend, so that to run suspend fun we need to use runBlockingTest,
        // allow us execute testBody inside an immediate execution dispatcher
        runBlocking {
            val heroSource = SearchHeroesSource(
                animeApi = animeApi,
                // this way we will receive 2 heroes as a result (multiple result)
                query = "Sa"
            )
            assertEquals<PagingSource.LoadResult<Int, Hero>>(
                //we need to confirm that the same class will be returned
                //when response is successful
                expected = PagingSource.LoadResult.Page(
                    data = listOf(heroes.first(), heroes[2]),
                    prevKey = null,
                    nextKey = null
                ),
                actual = heroSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )
        }
    }

    @Test
    fun `Search api with empty hero name, assert empty heroes list LoadingResult_Page`() {
        runBlocking {
            val heroSource = SearchHeroesSource(
                animeApi = animeApi,
                // check empty search
                query = ""
            )
            val loadResult = heroSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )

            val result = animeApi.searchHeroes("").heroes

            assertTrue { result.isEmpty() }
            assertTrue { loadResult is PagingSource.LoadResult.Page }
        }
    }

    @Test
    fun `Search api with non_existing hero name, assert empty heroes list LoadingResult_Page`() {
        runBlocking {
            val heroSource = SearchHeroesSource(
                animeApi = animeApi,
                // check empty search
                query = "Unknown"
            )
            val loadResult = heroSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )

            val result = animeApi.searchHeroes("Unknown").heroes

            assertTrue { result.isEmpty() }
            assertTrue { loadResult is PagingSource.LoadResult.Page }
        }
    }
}