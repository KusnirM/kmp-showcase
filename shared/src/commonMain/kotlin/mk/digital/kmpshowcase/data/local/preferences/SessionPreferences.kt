package mk.digital.kmpshowcase.data.local.preferences

interface SessionPreferences {
    suspend fun storeRefreshToken(refreshToken: String)
    suspend fun getRefreshToken(): String?
    suspend fun clearRefreshToken()
}

class SessionPreferencesImpl(private val preferences: Preferences) : SessionPreferences {

    override suspend fun storeRefreshToken(refreshToken: String) {
        preferences.putString(REFRESH_TOKEN_KEY, refreshToken)
    }

    override suspend fun getRefreshToken(): String? = preferences.getString(REFRESH_TOKEN_KEY)

    override suspend fun clearRefreshToken() {
        preferences.remove(REFRESH_TOKEN_KEY)
    }

    private companion object {
        private const val REFRESH_TOKEN_KEY = "refresh_token"
    }
}
