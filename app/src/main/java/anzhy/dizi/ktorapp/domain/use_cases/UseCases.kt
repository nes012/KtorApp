package anzhy.dizi.ktorapp.domain.use_cases

import anzhy.dizi.ktorapp.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import anzhy.dizi.ktorapp.domain.use_cases.get_selected_hero.GetSelectedHeroUseCase
import anzhy.dizi.ktorapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import anzhy.dizi.ktorapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import anzhy.dizi.ktorapp.domain.use_cases.search_heroes.SearchHeroesUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val getAllHeroesUseCase: GetAllHeroesUseCase,
    val searchHeroesUseCase: SearchHeroesUseCase,
    val getSelectedHeroUseCase: GetSelectedHeroUseCase
)
