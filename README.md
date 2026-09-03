![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)
![Room](https://img.shields.io/badge/Room-3DDC84?style=for-the-badge&logo=android&logoColor=white)

# StoicPass

Welcome to **StoicPass**, a minimal, secure, and modern password manager designed for your Android device. It stores your passwords entirely offline on your device, ensuring maximum privacy and security.

## Installation Guide (Windows)

### What You Need (Download These First)

1. **Java Development Kit (JDK) 17+**
   - Download from: https://www.oracle.com/java/technologies/downloads/#java17
   - Or use: https://adoptium.net/temurin/releases/?version=17
   - Install it (remember the installation folder)

2. **Android SDK**
   - Download Android Studio: https://developer.android.com/studio
   - Install Android Studio
   - Open Android Studio → Settings → Appearance & Behavior → System Settings → Android SDK
   - Install these:
     - Android SDK Platform (latest version, e.g., API 35)
     - Android SDK Build-Tools (latest)
     - Android Emulator (optional - only if you don't have a physical device)

3. **Git** (to download the code)
   - Download from: https://git-scm.com/download/win
   - Install it

### Step-by-Step Installation

**Step 1: Download StoicPass**
- Open Command Prompt (search "cmd" in Windows)
- Type:
```bash
git clone https://github.com/StoicDevansh/StoicPass.git
cd StoicPass
```

**Step 2: Build the App**
- In the same Command Prompt, type:
```bash
gradlew assembleDebug
```
- Wait 5-10 minutes (first build is slower). You'll see "BUILD SUCCESSFUL" at the end.
- The APK file is now at: `app\build\outputs\apk\debug\app-debug.apk`

**Step 3: Install on Your Phone**

**Option A: If you have an Android phone**
- Connect your phone to your PC with a USB cable
- On your phone: Go to Settings → About Phone → tap "Build Number" 7 times to enable Developer Mode
- Go to Settings → Developer Options → turn ON "USB Debugging"
- In Command Prompt, type:
```bash
gradlew installDebug
```
- The app will install automatically. Open it from your phone's app drawer.

**Option B: If you want to use an emulator**
- Open Android Studio → Virtual Device Manager → Create Virtual Device
- Start the emulator
- In Command Prompt, type:
```bash
gradlew installDebug
```
- The app will install in the emulator automatically

## Usage

1. **Launch** the app
2. **Add passwords** - Click to save credentials
3. **View & copy** - Browse saved passwords and copy them to clipboard
4. **Local storage** - Data stays on your device only

## Features

- ✅ No cloud storage (offline-first)
- ✅ No ads, no tracking
- ✅ Free forever
- ✅ Open source

## License

See LICENSE file for details.


