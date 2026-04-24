# Keep all classes in our package
-keep class com.iptv_trex.** { *; }

# Keep Hilt generated classes
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }

# Keep Retrofit models
-keepclasseswithmembernames class * {
    @retrofit2.http.* <methods>;
}

# Keep kotlinx serialization
-keepclasseswithmembers class kotlinx.serialization.** {
    kotlinx.serialization.KSerializer serializer(...);
}
-keep class kotlinx.serialization.**  { *; }
-keepclassmembers class kotlinx.serialization.** {
    *** Companion;
}
-keepclasseswithmembers class **.*$serializer {
    static **.*$Companion Companion;
}

# Keep Room
-keep class androidx.room.** { *; }
-keep @androidx.room.Entity class * { *; }
-keepclassmembers @androidx.room.Entity class * {
    public <init>(...);
}

# Keep OkHttp
-dontwarn okhttp3.**
-dontwarn okio.**

# Keep Timber
-dontwarn timber.**

# Keep Coroutines
-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}

# Keep Media3 / ExoPlayer
-keep class androidx.media3.** { *; }
-dontwarn androidx.media3.**

# Keep Firebase
-keep class com.google.firebase.** { *; }
-dontwarn com.google.firebase.**

# Keep Coil
-keep class coil.** { *; }
-dontwarn coil.**

# General rules
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile
