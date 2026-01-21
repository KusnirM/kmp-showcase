import SwiftUI
import shared


class AppDelegate : NSObject, UIApplicationDelegate, ObservableObject {

    override init() {
        KoinKt.doInitKoin()
    }
}
