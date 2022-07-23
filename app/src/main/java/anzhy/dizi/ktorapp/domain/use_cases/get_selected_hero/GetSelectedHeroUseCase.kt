package anzhy.dizi.ktorapp.domain.use_cases.get_selected_hero

import anzhy.dizi.ktorapp.data.repository.Repository
import anzhy.dizi.ktorapp.domain.model.Hero

class GetSelectedHeroUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(heroId: Int): Hero {
        return repository.getSelectedHero(heroId)
    }
}