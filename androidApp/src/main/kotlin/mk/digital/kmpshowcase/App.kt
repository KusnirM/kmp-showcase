package mk.digital.kmpshowcase

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.appcheck.appCheck
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory
import mk.digital.kmpshowcase.di.commonModule
import mk.digital.kmpshowcase.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class App : Application() {

    private val appConfig by lazy {
        AppConfig(
            buildType = if (BuildConfig.DEBUG) BuildType.DEBUG else BuildType.RELEASE
        )
    }

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initFb()
    }

    private fun initKoin() {
        initKoin(appConfig) {
            androidLogger()
            androidContext(this@App)
            modules(commonModule(appConfig))
        }
    }

    private fun initFb() {
        FirebaseApp.initializeApp(this)
        initFBAppCheck()
    }

    private fun initFBAppCheck() {
        Firebase.appCheck.installAppCheckProviderFactory(DebugAppCheckProviderFactory.getInstance())
    }
}
