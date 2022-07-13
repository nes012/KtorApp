package anzhy.dizi.ktorapp.domain.use_cases

import anzhy.dizi.ktorapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import anzhy.dizi.ktorapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase
)
