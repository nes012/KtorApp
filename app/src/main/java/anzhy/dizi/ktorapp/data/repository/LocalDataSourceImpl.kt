package anzhy.dizi.ktorapp.data.repository

import anzhy.dizi.ktorapp.data.local.AnimeDatabase
import anzhy.dizi.ktorapp.domain.model.Hero
import anzhy.dizi.ktorapp.domain.repository.LocalDataSource

class LocalDataSourceImpl(animeDatabase: AnimeDatabase): LocalDataSource {

    private val heroDao = animeDatabase.heroDao()

    override suspend fun getSelectedHero(heroId: Int): Hero {
        return heroDao.getSelectedHero(heroId = heroId)
    }
}