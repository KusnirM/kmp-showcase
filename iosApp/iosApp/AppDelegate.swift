import SwiftUI
import shared


class AppDelegate : NSObject, UIApplicationDelegate, ObservableObject {
    let backDispatcher: BackDispatcher = BackDispatcherKt.BackDispatcher()
    private var applicationLifecycle: ApplicationLifecycle
    @Published var appComponent: DefaultAppComponent

    
    override init() {
        KoinKt.doInitKoin()
        applicationLifecycle = ApplicationLifecycle()
        appComponent = DefaultAppComponent(
            componentContext: DefaultComponentContext(
                lifecycle: applicationLifecycle,
                stateKeeper : nil,
                instanceKeeper : nil,
                backHandler: backDispatcher
            )
        )
    }
}
