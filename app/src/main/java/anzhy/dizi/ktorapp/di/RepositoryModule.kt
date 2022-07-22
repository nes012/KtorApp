package anzhy.dizi.ktorapp.di

import android.content.Context
import anzhy.dizi.ktorapp.data.repository.DataStoreOperationsImpl
import anzhy.dizi.ktorapp.data.repository.Repository
import anzhy.dizi.ktorapp.domain.repository.DataStoreOperations
import anzhy.dizi.ktorapp.domain.use_cases.UseCases
import anzhy.dizi.ktorapp.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import anzhy.dizi.ktorapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import anzhy.dizi.ktorapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import anzhy.dizi.ktorapp.domain.use_cases.search_heroes.SearchHeroesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideDataStoreOperations(@ApplicationContext context: Context): DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository),
            getAllHeroesUseCase = GetAllHeroesUseCase(repository),
            searchHeroesUseCase = SearchHeroesUseCase(repository)
        )
    }

}