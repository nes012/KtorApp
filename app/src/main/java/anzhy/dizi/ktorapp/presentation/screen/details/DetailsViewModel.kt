package anzhy.dizi.ktorapp.presentation.screen.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import anzhy.dizi.ktorapp.domain.model.Hero
import anzhy.dizi.ktorapp.domain.use_cases.UseCases
import anzhy.dizi.ktorapp.util.Constants.DETAILS_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: UseCases,
    // this object we will use in order to fetch id
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectedHero: MutableStateFlow<Hero?> = MutableStateFlow(null)
    val selectedHero: StateFlow<Hero?> = _selectedHero

    //this code will execute immediately
    init {
        //Dispatchers.IO - because we're going to read from DB
        viewModelScope.launch(Dispatchers.IO) {
            val heroId = savedStateHandle.get<Int>(DETAILS_ARGUMENT_KEY)
            _selectedHero.value = heroId?.let {
                useCases.getSelectedHeroUseCase(heroId = heroId)
            }
            /*
            _selectedHero.value?.name?.let {  Log.d("Hero", it)   }
             */
        }
    }

}