package anzhy.dizi.ktorapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import anzhy.dizi.ktorapp.domain.model.Hero
import anzhy.dizi.ktorapp.data.local.dao.HeroDao
import anzhy.dizi.ktorapp.data.local.dao.HeroRemoteKeysDao
import anzhy.dizi.ktorapp.domain.model.HeroRemoteKeys

@Database(entities = [Hero::class, HeroRemoteKeys::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class AnimeDatabase: RoomDatabase() {

    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeysDao(): HeroRemoteKeysDao

}