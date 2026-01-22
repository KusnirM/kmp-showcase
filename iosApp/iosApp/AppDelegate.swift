import SwiftUI
import shared
import FirebaseCore


class AppDelegate : NSObject, UIApplicationDelegate, ObservableObject {

    private var buildType: String {
        Bundle.main.object(forInfoDictionaryKey: "BuildType") as? String ?? "debug"
    }

    private lazy var appConfig: AppConfig = {
        AppConfig(
            buildType: BuildType.companion.from(name: buildType)
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
