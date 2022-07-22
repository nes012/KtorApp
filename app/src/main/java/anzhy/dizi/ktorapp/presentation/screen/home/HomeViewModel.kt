package anzhy.dizi.ktorapp.presentation.screen.home

import androidx.lifecycle.ViewModel
import anzhy.dizi.ktorapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    useCases: UseCases
): ViewModel() {
    //we use cache mediator to cache info to DB
    val getAllHeroes = useCases.getAllHeroesUseCase()
}