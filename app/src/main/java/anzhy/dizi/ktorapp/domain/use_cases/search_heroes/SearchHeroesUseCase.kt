package anzhy.dizi.ktorapp.domain.use_cases.search_heroes

import androidx.paging.PagingData
import anzhy.dizi.ktorapp.data.repository.Repository
import anzhy.dizi.ktorapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class SearchHeroesUseCase(
    private val repository: Repository
) {
    operator fun invoke(query: String): Flow<PagingData<Hero>>{
        return repository.searchHeroes(query)
    }
}