# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-dontobfuscate

#keep all
-keep class com.itechart.covid_tracker.model.network.GetStats$Response$Cases { *; }

#-keep class androidx.** { *; }
#-keep class com.** { *; }
#-keep class android.** { *; }
#-keep class okhttp3.** { *; }
#-keep class java.** { *; }
#-keep class kotlinx.** { *; }
#-keep class kotlin.** { *; }
#-keep class okio.** { *; }
#-keep class retrofit2.** { *; }
#-keep class org.** { *; }
#-keep class javax.** { *; }
#-keep class dagger.** { *; }