# KMP Showcase

[![Kotlin](https://img.shields.io/badge/Kotlin-2.3-7F52FF.svg?logo=kotlin&logoColor=white)](https://kotlinlang.org)
[![Compose](https://img.shields.io/badge/Compose-Multiplatform-4285F4.svg?logo=jetpackcompose&logoColor=white)](https://www.jetbrains.com/lp/compose-multiplatform/)
[![Android](https://img.shields.io/badge/Android-36-3DDC84.svg?logo=android&logoColor=white)](/)
[![iOS](https://img.shields.io/badge/iOS-16-000000.svg?logo=apple&logoColor=white)](/)

**95% shared code** across Android & iOS

---

<table>
<tr>
<td width="50%">

### 🎨 UI & Navigation
- Compose Multiplatform
- Material 3 + Dark Mode
- Navigation3
- 40+ Components

</td>
<td width="50%">

### 📱 Platform APIs
- Biometrics (Face ID / Fingerprint)
- Camera & Gallery
- QR/Barcode Scanner
- Location & Permissions

</td>
</tr>
<tr>
<td width="50%">

### 🔌 Data & Network
- Ktor 3 HTTP Client
- SQLDelight Database
- DataStore Preferences
- Coil Image Loading

</td>
<td width="50%">

### 🔔 Notifications
- Push (FCM / APNs)
- Local Notifications
- Notification Channels
- Permission Handling

</td>
</tr>
</table>

---

## Tech Stack

<p>
<img src="https://img.shields.io/badge/Kotlin-7F52FF?logo=kotlin&logoColor=white" />
<img src="https://img.shields.io/badge/Compose-4285F4?logo=jetpackcompose&logoColor=white" />
<img src="https://img.shields.io/badge/Koin-F7A91E?logoColor=white" />
<img src="https://img.shields.io/badge/Ktor-7F52FF?logoColor=white" />
<img src="https://img.shields.io/badge/SQLDelight-005C99?logoColor=white" />
<img src="https://img.shields.io/badge/Firebase-FFCA28?logo=firebase&logoColor=black" />
<img src="https://img.shields.io/badge/Detekt-6F42C1?logoColor=white" />
<img src="https://img.shields.io/badge/Mokkery-FF6B6B?logoColor=white" />
</p>

---

## Screens

| | | | |
|:---:|:---:|:---:|:---:|
| 🔐 **Login** | 📝 **Register** | 🏠 **Home** | 🎨 **Components** |
| 🌐 **Networking** | 💾 **Storage** | 🗄️ **Database** | 📱 **Platform APIs** |
| 📷 **Scanner** | 📅 **Calendar** | 🔔 **Notifications** | ⚙️ **Settings** |

---

## Architecture

```
Presentation  →  Domain  →  Data
  (UI/VM)       (UseCase)   (Repository)
```

---

## Quick Start

```bash
# Android
./gradlew :androidApp:installDebug

# iOS
open iosApp/iosApp.xcodeproj
```

---

## Project Structure

```
androidApp/     Android app
iosApp/         iOS app (SwiftUI shell)
shared/         Shared KMP module (UI, domain, data)
```

---

## Roadmap

- [ ] Pagination
- [ ] Deep links
- [ ] Maps
- [ ] Video player
- [ ] Offline-first
