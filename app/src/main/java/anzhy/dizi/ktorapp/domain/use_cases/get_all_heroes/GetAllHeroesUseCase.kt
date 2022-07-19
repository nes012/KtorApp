package anzhy.dizi.ktorapp.domain.use_cases.get_all_heroes

import androidx.paging.PagingData
import anzhy.dizi.ktorapp.data.repository.Repository
import anzhy.dizi.ktorapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class GetAllHeroesUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<Hero>>{
        return repository.getAllHeroes()
    }
}