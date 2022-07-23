package anzhy.dizi.ktorapp.domain.repository

import anzhy.dizi.ktorapp.domain.model.Hero

interface LocalDataSource {
    suspend fun getSelectedHero(heroId: Int): Hero
}