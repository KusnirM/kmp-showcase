import SwiftUI

@main
struct iOSApp: App {

    @UIApplicationDelegateAdaptor(AppDelegate.self)
    var appDelegate: AppDelegate

      init() {
            // Firebase initialization
//             FirebaseApp.configure()
        }

	var body: some Scene {
		WindowGroup {
			IosAppView(
			appDelegate: appDelegate
			)
		}
	}
}

struct IosAppView : View {
    @ObservedObject
    var appDelegate: AppDelegate


    var body: some View {
        ContentView(component: appDelegate.appComponent,backDispatcher: appDelegate.backDispatcher)
    }
}
