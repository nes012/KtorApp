package anzhy.dizi.ktorapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import anzhy.dizi.ktorapp.data.local.AnimeDatabase
import anzhy.dizi.ktorapp.util.Constants.ANIME_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AnimeDatabase {
        return Room.databaseBuilder(
            context,
            AnimeDatabase::class.java,
            ANIME_DATABASE
        ).build()
    }
}