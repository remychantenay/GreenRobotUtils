# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/remychantenay/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Gson specific
-keepattributes Signature
-keep class sun.misc.Unsafe { *; }

-keep class java.util.** { *; }
-keep class * implements java.io.Serializable { *; }
-keepattributes *Annotation*
-keepclassmembers class **.R$* {
    public static <fields>;
}

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
