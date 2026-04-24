@echo off
setlocal
set JAVA_HOME="C:\Program Files\Android\Android Studio\jbr"
set PATH=%JAVA_HOME%\bin;%PATH%

echo Building IPTV-TREX Android APK...
echo JAVA_HOME=%JAVA_HOME%

cd /d "%~dp0"

REM Download Gradle wrapper
echo Downloading Gradle...
powershell -Command "& {Invoke-WebRequest -Uri 'https://services.gradle.org/distributions/gradle-8.2-bin.zip' -OutFile 'gradle-8.2-bin.zip'}"
powershell -Command "& {Expand-Archive -Path 'gradle-8.2-bin.zip' -DestinationPath 'gradle-temp'}"
move gradle-temp\gradle-8.2\* gradle\
rmdir /s /q gradle-temp
del gradle-8.2-bin.zip

REM Build the APK
echo Starting build...
.\gradlew assembleRelease

echo Build complete!
endlocal
