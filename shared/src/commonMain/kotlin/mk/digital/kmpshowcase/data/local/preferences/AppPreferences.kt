package mk.digital.kmpshowcase.data.local.preferences

interface AppPreferences {
    suspend fun getFcmToken(): String?
    suspend fun setFcmToken(value: String)
}

class AppPreferencesImpl(private val preferences: Preferences) : AppPreferences {

    override suspend fun getFcmToken(): String? = preferences.getString(FB_TOKEN_KEY)
    override suspend fun setFcmToken(value: String) = preferences.putString(FB_TOKEN_KEY, value)

    private companion object {
        private const val FB_TOKEN_KEY = "fcm_token"
    }
}
