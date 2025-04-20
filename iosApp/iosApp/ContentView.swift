import UIKit
import SwiftUI
import shared

struct ComposeView: UIViewControllerRepresentable {
    let component: DefaultAppComponent
     let backDispatcher: BackDispatcher

    func makeUIViewController(context: Context) -> UIViewController {
        Main_iosKt.MainViewController(component: component, backDispatcher: backDispatcher)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
let component: DefaultAppComponent
     let backDispatcher: BackDispatcher

    var body: some View {
        ComposeView(component: component, backDispatcher: backDispatcher)
        .ignoresSafeArea(.keyboard)
    }
}

