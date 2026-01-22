import SwiftUI
import shared
import FirebaseCore
import FirebaseCrashlytics


class AppDelegate : NSObject, UIApplicationDelegate, ObservableObject {

    private var buildTypeString: String {
        Bundle.main.object(forInfoDictionaryKey: "BuildType") as? String ?? "debug"
    }

    private lazy var appConfig: AppConfig = {
        return AppConfig(
            buildType: BuildType.companion.from(name: buildTypeString)
        )
    }()

    override init() {
        super.init()
        KoinKt.doInitKoin(appConfig: appConfig)
    }

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        FirebaseApp.configure()
        return true
    }
}
