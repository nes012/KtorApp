package anzhy.dizi.ktorapp.util

object Constants {

    //to access local host we need to write this address and not localhost
    const val BASE_URL = "http://10.0.2.2:8080"
    //const val BASE_URL = "http://localhost:8080"

    const val DETAILS_ARGUMENT_KEY = "heroId"

    const val ANIME_DATABASE = "anime_database"
    const val HERO_DATABASE_TABLE = "hero_table"
    const val HERO_REMOTE_KEY_DATABASE_TABLE = "hero_remote_keys_table"

    const val PREFERENCES_NAME = "anime_preferences"
    const val PREFERENCES_KEY = "on_boarding_completed"

    const val ON_BOARDING_PAGE_COUNT = 3
    const val LAST_ON_BOARDING_PAGE = 2

    const val ITEMS_PER_PAGE = 3

    const val ABOUT_TEXT_MAX_LINES = 7
    const val MIN_BACKGROUND_IMAGE_HEIGHT = 0.4f
}