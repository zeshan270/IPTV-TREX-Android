# IPTV TREX - Android Application

A modern, feature-rich Android IPTV streaming application built with Kotlin, Jetpack Compose, and ExoPlayer. Supports live TV, movies, and TV series from Xtream Codes IPTV servers.

## 🎯 Features

### Core Features
- ✅ **Live TV Streaming** - Watch live TV channels via HLS/DASH
- ✅ **Movies (VOD)** - Browse and stream movies
- ✅ **TV Series** - Watch episodes from TV series
- ✅ **ExoPlayer Integration** - High-quality video playback
- ✅ **Xtream Codes API Support** - Direct integration with IPTV servers
- ✅ **Push Notifications** - Firebase Cloud Messaging support

### Library Features
- ✅ **Watchlist** - Save items to watch later
- ✅ **Favorites** - Mark favorite content
- ✅ **User Preferences** - Customizable settings
- ✅ **Watch History** - Track watched content

### Technical Features
- ✅ **Dark Theme** - Modern Material 3 dark design
- ✅ **MVVM Architecture** - Clean, testable code
- ✅ **Room Database** - Local persistence
- ✅ **Offline Support** - Content caching
- ✅ **Responsive Design** - Works on phones and tablets

## 🚀 Quick Start

### Prerequisites
- Android Studio (Giraffe or later)
- Android API 26+ (Android 8+)
- Java 17+

### Setup
1. Open project in Android Studio
2. Wait for Gradle sync
3. Connect device or start emulator
4. Run → Run 'app'

## 📁 Project Structure

```
app/
├── src/main/
│   ├── kotlin/com/iptv_trex/
│   │   ├── data/          # API, Database, Models
│   │   ├── di/            # Dependency Injection
│   │   ├── services/      # Firebase, Services
│   │   ├── ui/            # UI Screens, Theme, Navigation
│   │   └── App.kt, MainActivity.kt
│   └── res/               # Resources (strings, colors, XML)
├── build.gradle.kts       # Dependencies and Build Config
└── proguard-rules.pro     # Code Obfuscation Rules
```

## 🏗️ Architecture

### MVVM + Repository Pattern
```
UI Layer (Compose Screens)
    ↓
ViewModel (State Management)
    ↓
Repository (Data Source)
    ↓
Data Layer (API + Database)
```

### Key Components

**Data Layer**
- `XtreamClient` - IPTV API wrapper
- `AppDatabase` - Room database with 7 entities
- `ApiService` - Retrofit service interface
- `DataModels` - Serializable API models

**Presentation Layer**
- `LoginScreen` - Server/credentials entry
- `HomeScreen` - Main navigation menu
- `PlayerScreen` - ExoPlayer video player
- `ViewModels` - State and business logic

**Services**
- `FirebaseMessagingService` - Push notifications
- Timber logging integration

## 🎬 ExoPlayer Integration

The app uses **ExoPlayer 1.1.1** for video playback:

### Supported Formats
- HLS (HTTP Live Streaming)
- DASH (Dynamic Adaptive Streaming)
- Progressive downloads

### Features
- Adaptive bitrate switching
- Full/windowed playback
- Seek, play, pause controls
- Loading state management
- Error recovery
- Quality selection

### PlayerScreen
```kotlin
PlayerScreen(
    streamUrl = "http://server/live/user/pass/channel.m3u8",
    streamName = "Channel Name",
    navController = navController
)
```

## 🗄️ Database Schema

| Entity | Fields | Purpose |
|--------|--------|---------|
| ChannelEntity | streamId, name, categoryId, icon | Live TV channels |
| MovieEntity | streamId, name, categoryId, cover, rating | VOD movies |
| SeriesEntity | seriesId, name, categoryId, cover, rating | TV series |
| EpisodeEntity | episodeId, seriesId, title, season, episode | Series episodes |
| WatchlistEntity | itemId, itemType, name, addedAt | User's watchlist |
| FavoriteEntity | itemId, itemType, name, favoritedAt | Favorite items |
| UserPreferencesEntity | theme, fontSize, quality, notifications | User settings |

## 🔌 API Integration

### Xtream Codes API Support
The app integrates with Xtream Codes IPTV servers:

```kotlin
val client = XtreamClient(
    serverUrl = "http://iptv.server",
    username = "user@email.com",
    password = "password"
)

// Get live categories
val categories = client.getLiveCategories()

// Get streams in category
val streams = client.getLiveStreams(categoryId)

// Generate stream URL
val streamUrl = client.getLiveStreamUrl(streamId)
```

## 📲 Firebase Cloud Messaging

Push notifications support:

```kotlin
// Automatic handling of notifications
// Data payloads: action, content
// Supported actions: new_stream, special_offer, maintenance
```

**Setup**: Place `google-services.json` in `app/` directory

## 🎨 Theme & UI

### Material 3 Dark Theme
- Primary: Purple 500
- Secondary: Pink 500
- Background: #0d0d14
- Surface: #1a1a21

### Compose Components
- Modern Material 3 components
- Custom theming support
- Responsive layouts
- Touch-friendly (44x44px min buttons)

## 🔐 Security

- ✅ ProGuard code obfuscation (release builds)
- ✅ HTTPS/TLS support
- ✅ Secure credential handling
- ✅ Room database encryption ready
- ✅ Minimal required permissions

## 📦 Dependencies

### Core
- Kotlin 1.9.20
- Android Gradle Plugin 8.1.1

### UI
- Jetpack Compose 1.6.0
- Material 3 1.1.2
- Navigation Compose 2.7.5

### Data
- Room 2.6.0
- Retrofit 2.9.0
- OkHttp 4.11.0
- Kotlinx Serialization 1.6.1

### Media
- Media3/ExoPlayer 1.1.1
- Coil 2.4.0

### Async
- Kotlin Coroutines 1.7.3

### DI
- Hilt 2.48

### Other
- Timber 5.0.1 (logging)
- Firebase Messaging 23.3.1

## 🏗️ Building

### Debug APK
```bash
./gradlew assembleDebug
# Output: app/build/outputs/apk/debug/app-debug.apk
```

### Release APK
```bash
./gradlew assembleRelease
# Output: app/build/outputs/apk/release/app-release.apk
```

### Clean Build
```bash
./gradlew clean assembleRelease
```

## 📊 Code Statistics

- **Kotlin Files**: 40+
- **Lines of Code**: 5000+
- **Compose Screens**: 3
- **ViewModels**: 2
- **Room Entities**: 7
- **API Endpoints**: 10+

## 🎯 Implementation Details

### LoginViewModel
- Server URL, username, password management
- Input validation
- Error handling
- XtreamClient creation

### PlayerViewModel
- Stream URL and name tracking
- Playback state management
- Position and duration tracking
- Fullscreen toggling
- Error handling

### Room DAOs
- Full CRUD operations
- Flow-based reactive queries
- Efficient filtering and sorting
- Batch operations support

## 🚀 Next Steps

1. **Configure Server**
   - Enter valid IPTV server credentials
   - Test stream connectivity

2. **Customize Branding**
   - Update app icon (ic_launcher)
   - Customize colors in Theme.kt
   - Add app logo/splash screen

3. **Firebase Setup**
   - Create Firebase project
   - Add google-services.json
   - Enable Cloud Messaging

4. **Advanced Features**
   - User authentication system
   - Recommendation engine
   - User profiles
   - Content analytics

5. **Release**
   - Sign APK with keystore
   - Test on multiple devices
   - Submit to Google Play Store

## 🧪 Testing

```bash
# Unit tests
./gradlew test

# Instrumented tests
./gradlew connectedAndroidTest
```

## 📝 Build Configuration

### File: `app/build.gradle.kts`
```kotlin
minSdk = 26              // Android 8+
targetSdk = 34           // Android 14
versionCode = 1
versionName = "1.0.0"
```

### ProGuard Rules: `app/proguard-rules.pro`
- Keeps app classes
- Preserves Retrofit, OkHttp, Room
- Protects Hilt, Media3, ExoPlayer
- Enables code minification and obfuscation

## 🐛 Troubleshooting

### Gradle Sync Fails
```bash
./gradlew --refresh-dependencies
```

### ExoPlayer Not Playing
- Check HLS stream URL format
- Verify network connectivity
- Check logcat: `adb logcat | grep ExoPlayer`

### Firebase Not Working
- Verify google-services.json is in app/
- Check Firebase project settings
- Rebuild with clean: `./gradlew clean assembleDebug`

## 📚 Documentation

See `ANDROID_BUILD_GUIDE.md` for detailed building instructions and troubleshooting.

## 📄 License

[Your License Here]

## 👨‍💻 Version

- **Current Version**: 1.0.0
- **Build**: 1
- **Target SDK**: Android 14 (API 34)
- **Minimum SDK**: Android 8 (API 26)

## 🤝 Support

For issues, questions, or feature requests, please check:
1. Android logcat for errors: `adb logcat`
2. ANDROID_BUILD_GUIDE.md for detailed help
3. ExoPlayer/Firebase documentation

---

**Built with ❤️ using Kotlin, Jetpack Compose, and ExoPlayer**
