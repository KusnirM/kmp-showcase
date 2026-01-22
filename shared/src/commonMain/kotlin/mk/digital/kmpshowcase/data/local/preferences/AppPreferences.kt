package mk.digital.kmpshowcase.data.local.preferences

interface AppPreferences {
    suspend fun getPersistentCounter(): Int
    suspend fun setPersistentCounter(value: Int)

    suspend fun getThemeMode(): String
    suspend fun setThemeMode(mode: String)
}

class AppPreferencesImpl(private val preferences: Preferences) : AppPreferences {

    override suspend fun getPersistentCounter(): Int = preferences.getInt(PERSISTENT_COUNTER_KEY) ?: 0
    override suspend fun setPersistentCounter(value: Int) = preferences.putInt(PERSISTENT_COUNTER_KEY, value)

    override suspend fun getThemeMode(): String = preferences.getString(THEME_MODE_KEY) ?: DEFAULT_THEME_MODE
    override suspend fun setThemeMode(mode: String) = preferences.putString(THEME_MODE_KEY, mode)

    private companion object {
        private const val PERSISTENT_COUNTER_KEY = "persistent_counter"
        private const val THEME_MODE_KEY = "theme_mode"
        private const val DEFAULT_THEME_MODE = "SYSTEM"
    }
}
