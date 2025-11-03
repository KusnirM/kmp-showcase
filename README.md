@ -1,3 +1,129 @@
# KMPSample — Kotlin Multiplatform App Template

A lean Kotlin Multiplatform (KMP) starter with **shared business/UI code** and **native shells** for Android and iOS.  
Out of the box you get **bottom bar navigation**, **safe-area / edge‑to‑edge handling**, DI, navigation, networking, and a modern toolkit stack.

## TL;DR

- **KMP shared**: UI (Compose Multiplatform), navigation (Decompose), DI (Koin), networking (Ktor 3), JSON (kotlinx-serialization), coroutines, datetime.
- **Android**: Material 3, Activity Compose, edge‑to‑edge, previews.
- **iOS**: Compose MPP UI hosted in Swift/SwiftUI shell, safe‑area support, Darwin HTTP engine.
- **Ready for feature screens** in bottom bar and simple per‑screen DI.

---

## Features

- ✅ **Bottom bar** navigation (tabs) with `Decompose` child stacks
- ✅ **Safe area / edge‑to‑edge** paddings handled for both Android & iOS
- ✅ **Koin** modules for easy dependency wiring
- ✅ **Ktor 3** client with ContentNegotiation(JSON), Logging, Timeouts
- ✅ **Coil 3** image loading (with Ktor network integration)
- ✅ **Resource/version catalog** via `libs.versions.toml`
- ✅ **Unit tests** on common + JVM (JUnit5 + MockK)

---

## Tech Stack (from `libs.versions.toml`)

**Kotlin / Compose**
- Kotlin: `2.1.20`
- Compose (Android): `1.7.8`
- Compose Multiplatform: `1.7.3`
- Activity Compose: `1.10.1`
- Material3 (Android): `1.3.2`

**Navigation / Lifecycle**
- Decompose: `3.3.0`
- Essenty: `2.5.0`

**DI**
- Koin: `4.0.0` (`core`, `android`, `compose`, `compose-viewmodel`)

**Networking / Serialization**
- Ktor: `3.0.0` (`client-core`, `okhttp`, `darwin`, `content-negotiation`, `logging`, `serialization-kotlinx-json`)
- kotlinx-serialization-json: `1.8.0`

**Concurrency / Time**
- kotlinx-coroutines: `1.10.1`
- kotlinx-datetime: `0.6.2`

**Images**
- Coil 3: `3.1.0` (`coil-compose`, `coil-svg`, `coil-network-ktor3`)

**Testing**
- JUnit 5: `5.10.2`
- MockK: `1.13.10`

**Misc**
- SLF4J simple: `2.0.9`
- Android Gradle Plugin: `8.11.2`
- Android SDKs: min `26`, target/compile `36`

---

## Module Structure

```
root
├─ androidApp/    # Android application (Compose, Material3, Android entry points)
├─ iosApp/        # iOS application (Swift/SwiftUI host, LaunchScreen)
└─ shared/        # KMP shared code (Compose MPP UI, domain, data, DI, navigation)
```

- **shared** contains:
    - UI screens (Compose MPP) wired via **Decompose**
    - DI modules with **Koin**
    - **Ktor client** config (JSON, logging, timeouts)
    - Common models, use‑cases, resources

---

## Architecture & Conventions

- **UI**: Compose MPP with M3 styling on Android; safe‑area courtesy of Compose Insets/WindowInsets APIs and platform wrappers.
- **Navigation**: `Decompose` child stacks per tab. Each tab has its own router & component state.
- **DI**: `Koin` modules in `shared` (feature‑scoped modules if needed). Android initializes Koin in `Application`, iOS during app launch.
- **Networking**: `Ktor 3`
    - Android → OkHttp engine
    - iOS → Darwin engine
    - JSON via `kotlinx.serialization` + `ContentNegotiation`
    - Logging via `ktor-client-logging`
- **Images**: `Coil 3` with `coil-network-ktor3`
- **Coroutines**: Structured concurrency; never block the main thread.

---

## Internationalization (i18n)

- String resources live in shared.
- **Android**: App‑specific locales supported (API 33+); fallback to default below that.
- **iOS**: Changing language at runtime may require app restart; template keeps it simple.

---

## Logging

- Common logging facade in `shared` (expect/actual).
- Android → `Logcat`, iOS → `NSLog`.
- For network, enable `Ktor` logging (already wired).

---

## Roadmap / TODO

- [ ] Release build variant, signing config
- [ ] Sample feature module with DI & navigation
- [ ] Theming polish & dark mode toggle
- [ ] Local persistence sample 
- [ ] Crash reporting toggles per build type
- [ ] CI: GitHub Actions (assemble, tests)

---
