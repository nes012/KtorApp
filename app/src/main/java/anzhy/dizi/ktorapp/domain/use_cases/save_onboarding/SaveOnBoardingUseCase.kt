package anzhy.dizi.ktorapp.domain.use_cases.save_onboarding

import anzhy.dizi.ktorapp.data.repository.Repository

class SaveOnBoardingUseCase(
    private val repository: Repository
) {
    // function with invoke will be executed immediately when class called
    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnBoardingState(completed)
    }
}