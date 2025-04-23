# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:

# If you keep the line number information, uncomment this to
# hide the original kr.co.data.source file name.
#-renamesourcefileattribute SourceFile

### Gson ProGuard and R8 rules which are relevant for all users
### This file is automatically recognized by ProGuard and R8, see https://developer.android.com/build/shrink-code#configuration-files
###
### IMPORTANT:
### - These rules are additive; don't include anything here which is not specific to Gson (such as completely
###   disabling obfuscation for all classes); the user would be unable to disable that then
### - These rules are not complete; users will most likely have to add additional rules for their specific
###   classes, for example to disable obfuscation for certain fields or to keep no-args constructors
###

-keep class io.ktor.** { *; }
-keep class kotlinx.serialization.** { *; }
-keep @kotlinx.serialization.Serializable class ** { *; }

-keep class kr.co.kurly.remote.model.** { *; }

-dontwarn io.ktor.**
-dontnote kotlinx.serialization.**
-dontwarn kotlinx.serialization.**

# Keep generic signatures; needed for correct type resolution
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable