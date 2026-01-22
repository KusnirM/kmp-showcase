import SwiftUI
import UIKit
import shared

@main
struct iOSApp: App {

    @UIApplicationDelegateAdaptor(AppDelegate.self)
    var appDelegate: AppDelegate

    var body: some Scene {
        WindowGroup {
            IosAppView(appDelegate: appDelegate)
        }
    }
}

struct IosAppView: View {
    @ObservedObject
    var appDelegate: AppDelegate

    var body: some View {
        ComposeView()
            .ignoresSafeArea(.keyboard)
    }
}

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        Main_iosKt.MainViewController(onOpenSettings: openSettings)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}

    private func openSettings() {
        if let url = URL(string: UIApplication.openSettingsURLString) {
            UIApplication.shared.open(url)
        }
    }
}
