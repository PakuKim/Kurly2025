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

# Keep generic signatures; needed for correct type resolution
-keepattributes Signature

# Keep Gson annotations
# Note: Cannot perform finer selection here to only cover Gson annotations, see also https://stackoverflow.com/q/47515093
-keepattributes *Annotation*

# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# OkHttp platform used only on JVM and when Conscrypt dependency is available.
-dontwarn okhttp3.internal.platform.ConscryptPlatform

-keepclassmembers enum kr.co.remote.model.**{ *; }

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit

# With R8 full mode generic signatures are stripped for classes that are not
# kept. Suspend functions are wrapped in continuations where the type argument
# is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

# R8 full mode strips generic signatures from return types if not kept.
-keep,allowoptimization,allowshrinking,allowobfuscation class <3>