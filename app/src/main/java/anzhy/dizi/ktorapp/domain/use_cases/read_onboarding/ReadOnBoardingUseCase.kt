package anzhy.dizi.ktorapp.domain.use_cases.read_onboarding

import anzhy.dizi.ktorapp.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<Boolean> {
       return repository.readOnBoardingState()
    }
}