# IPTV TREX Android - Build Guide

## Project Structure

```
IPTV-TREX-Android/
├── app/
│   ├── build.gradle.kts                    # App-level Gradle configuration
│   ├── proguard-rules.pro                  # ProGuard obfuscation rules
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml         # App manifest
│           ├── kotlin/com/iptv_trex/
│           │   ├── MainActivity.kt         # App entry point
│           │   ├── App.kt                  # Hilt initialization
│           │   ├── data/
│           │   │   ├── api/               # API clients and models
│           │   │   ├── db/                # Room database
│           │   │   └── model/             # Data models
│           │   ├── di/                    # Dependency injection modules
│           │   ├── services/              # Services (Firebase, etc.)
│           │   └── ui/
│           │       ├── navigation/        # Navigation setup
│           │       ├── screens/           # Compose screens
│           │       ├── theme/             # Theming
│           │       └── viewmodels/        # ViewModels
│           └── res/
│               ├── values/                # String/color/theme resources
│               └── xml/                   # Backup and data extraction rules
├── build.gradle.kts                       # Root Gradle configuration
├── settings.gradle.kts                    # Gradle settings
├── gradle/                                # Gradle wrapper
├── gradlew                                # Gradle wrapper script
└── README.md
```

## Prerequisites

1. **Android Studio** (Giraffe or later recommended)
   - Download from: https://developer.android.com/studio
   - Includes Android SDK, emulator, and build tools

2. **Android SDK**
   - API Level 26+ (Android 8+)
   - Build tools 34.0.0+

3. **Java Development Kit (JDK)**
   - Java 17 or later
   - Included with Android Studio

## Building via Android Studio (Recommended)

### Step 1: Open Project
1. Launch Android Studio
2. File → Open → Select `IPTV-TREX-Android` directory
3. Wait for Gradle sync to complete

### Step 2: Configure Firebase (Optional)
For push notifications to work:
1. Create a Firebase project at https://console.firebase.google.com
2. Add Android app to the project
3. Download `google-services.json`
4. Place in `app/` directory

### Step 3: Build APK
1. Build → Build Bundle(s) / APK(s) → Build APK(s)
2. Wait for build to complete
3. APK will be located at: `app/build/outputs/apk/release/app-release.apk`

### Step 4: Install on Device/Emulator
1. Connect Android device via USB or start emulator
2. Run → Run 'app'
3. Select target device
4. App will install and launch

## Building via Command Line

### Generate Debug APK
```bash
cd IPTV-TREX-Android
./gradlew assembleDebug
```
Output: `app/build/outputs/apk/debug/app-debug.apk`

### Generate Release APK
```bash
./gradlew assembleRelease
```
Output: `app/build/outputs/apk/release/app-release.apk`

### Run Tests
```bash
./gradlew test
```

### Clean Build
```bash
./gradlew clean assembleRelease
```

## Architecture Overview

### Technology Stack
- **Language**: Kotlin 1.9.20
- **UI Framework**: Jetpack Compose
- **Database**: Room (SQLite)
- **Networking**: Retrofit 2.9.0 + OkHttp
- **Media**: ExoPlayer 1.1.1 (HLS/DASH)
- **Notifications**: Firebase Cloud Messaging
- **Dependency Injection**: Hilt
- **State Management**: ViewModel + Coroutines
- **Serialization**: Kotlinx Serialization

### Key Components

#### Data Layer
- **XtreamClient**: Wraps Xtream Codes API
- **AppDatabase**: Room database with 7 entities
- **DAOs**: Database access objects for CRUD operations
- **ApiService**: Retrofit service interface

#### Presentation Layer
- **LoginScreen**: Server, username, password entry
- **HomeScreen**: Main menu with browse options
- **PlayerScreen**: ExoPlayer-based video player
- **ViewModels**: State management and business logic

#### Features
1. **Live TV** - Stream live channels
2. **Movies** - Browse and play movies
3. **Series** - Browse and play TV series
4. **Watchlist** - Save items for later
5. **Favorites** - Mark favorite items
6. **Settings** - Preferences and configuration
7. **Video Player** - ExoPlayer with HLS support
8. **Push Notifications** - Firebase Cloud Messaging

## ExoPlayer Integration

The app uses ExoPlayer for video playback with support for:
- HLS (HTTP Live Streaming)
- DASH (Dynamic Adaptive Streaming over HTTP)
- Progressive downloads
- Adaptive bitrate switching

### PlayerScreen Features
- Full and windowed playback
- ExoPlayer UI controls (play, pause, seek, volume)
- Stream quality management
- Automatic loading state management
- Error handling and recovery

## Database Schema

### Entities
1. **ChannelEntity** - Live TV channels
2. **MovieEntity** - VOD movies
3. **SeriesEntity** - TV series
4. **EpisodeEntity** - Series episodes
5. **WatchlistEntity** - User's watchlist
6. **FavoriteEntity** - Favorite items
7. **UserPreferencesEntity** - App settings

## API Integration

### Xtream Codes API
The app communicates with Xtream Codes IPTV servers:
- User authentication
- Category fetching
- Stream listing
- Stream URL generation
- Content information

### Firebase Cloud Messaging
- Push notification delivery
- Custom data payload handling
- Token management

## Configuration

### Build Variants
- **Debug**: Development build with logging
- **Release**: Optimized build with ProGuard obfuscation

### Gradle Configuration
Edit `app/build.gradle.kts`:
- `minSdk = 26` (Android 8)
- `targetSdk = 34` (Android 14)
- `versionCode = 1`
- `versionName = "1.0.0"`

### ProGuard Rules
File: `app/proguard-rules.pro`
- Preserves app classes
- Keeps Retrofit/OkHttp
- Keeps Room, Hilt, Media3
- Keeps ExoPlayer components

## Troubleshooting

### Build Failures

**Gradle sync fails**
```bash
./gradlew --refresh-dependencies
```

**Java version mismatch**
- Update Java to 17+
- In Android Studio: File → Project Structure → JDK location

**Missing SDK**
- Run Android Studio SDK Manager: Tools → SDK Manager
- Install API 26-34 and build tools 34.0.0+

### Runtime Issues

**ExoPlayer errors**
- Check HLS stream URL format
- Verify network access
- Check logcat for detailed errors

**Firebase not initialized**
- Add google-services.json to app/ directory
- Rebuild the app

**Database errors**
- Clear app data: Settings → Apps → IPTV TREX → Storage → Clear data
- Reinstall the app

## Performance Optimization

### Enabled by Default
1. ProGuard minification and obfuscation
2. Resource shrinking
3. DEX optimization
4. Code obfuscation

### Memory Management
- ExoPlayer caching enabled
- Database indexes on frequently queried columns
- Image caching via Coil

## Security Features

1. **SSL/TLS** - Secure network communication
2. **ProGuard** - Code obfuscation
3. **Data Encryption** - Room database encryption ready
4. **Cleartext Traffic** - HTTP allowed for local IPTV servers
5. **Permissions** - Minimal required permissions

## Testing

### Unit Tests
```bash
./gradlew test
```

### Instrumented Tests (on device/emulator)
```bash
./gradlew connectedAndroidTest
```

## Firebase Setup (Optional)

For push notifications:

1. Create Firebase Project
   - https://console.firebase.google.com
   - Create new project or select existing

2. Add Android App
   - Add Android app to Firebase
   - Download `google-services.json`

3. Configure Project
   - Copy `google-services.json` to `app/`
   - Add to root `build.gradle.kts`:
     ```kotlin
     id("com.google.gms.google-services") version "4.3.15" apply false
     ```
   - Add to `app/build.gradle.kts`:
     ```kotlin
     id("com.google.gms.google-services")
     ```

4. Rebuild
   ```bash
   ./gradlew clean assembleDebug
   ```

## Release Checklist

- [ ] Update version code and name in `build.gradle.kts`
- [ ] Test on multiple device sizes and Android versions
- [ ] Run ProGuard mapping tests
- [ ] Test all ExoPlayer playback scenarios
- [ ] Verify Firebase notifications
- [ ] Check database migrations
- [ ] Test watchlist and favorites persistence
- [ ] Performance test with large content libraries

## Next Steps

1. Configure IPTV server connection
2. Test with live streams
3. Customize branding (app icon, colors)
4. Set up Firebase for notifications
5. Implement advanced features (recommendations, user profiles)
6. Release on Google Play Store

## Support

For issues or questions:
1. Check Android logcat: `adb logcat`
2. Review Gradle output for build errors
3. Verify network connectivity
4. Check IPTV server credentials
5. Review Compose/ExoPlayer documentation

## License

[Your License Here]
