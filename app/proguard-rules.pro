-keep interface kr.co.kurly.core.ui.base.BaseViewModel implements android.os.Parcelable { *; }
-keep class kr.co.kurly.domain.model.** implements android.os.Parcelable { *; }
-keepclassmembers class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

-keep class androidx.navigation.** { *; }
-dontwarn androidx.navigation.**

-keep @androidx.compose.runtime.Composable class * { *; }
-keep class androidx.compose.** { *; }

-keep class androidx.activity.ComponentActivity { *; }
-keep class androidx.lifecycle.ViewModel { *; }

-keepclassmembers class **.R$* {
    public static <fields>;
}
-keep class **.R$*

-keepattributes Signature
-keepattributes SourceFile,LineNumberTable

-dontwarn org.slf4j.impl.StaticLoggerBinder
-dontwarn org.slf4j.impl.StaticMDCBinder

# With R8 full mode generic signatures are stripped for classes that are not
# kept. Suspend functions are wrapped in continuations where the type argument
# is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation