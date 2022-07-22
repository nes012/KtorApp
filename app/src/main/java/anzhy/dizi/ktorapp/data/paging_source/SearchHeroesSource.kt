package anzhy.dizi.ktorapp.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import anzhy.dizi.ktorapp.data.remote.AnimeApi
import anzhy.dizi.ktorapp.domain.model.Hero
import javax.inject.Inject

class SearchHeroesSource @Inject constructor(
    private val animeApi: AnimeApi,
    private val query: String
): PagingSource<Int,Hero>() {
    //define how to paginate source code
    override fun getRefreshKey(state: PagingState<Int, Hero>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hero> {
        return try {
            val apiResponse = animeApi.searchHeroes(name = query)
            val heroes = apiResponse.heroes
            if(heroes.isNotEmpty()){
                LoadResult.Page(
                    data = heroes,
                    prevKey = apiResponse.prevPage,
                    nextKey = apiResponse.nextPage
                )
            }
            else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}