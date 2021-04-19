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
#keep 保留类和类中的成员，防止被混淆或移除
#keepnames 保留类和类中的成员，防止被混淆，成员没有被引用会被移除
#keepclassmembers 只保留类中的成员，防止被混淆或移除
#keepclassmembernames 只保留类中的成员，防止被混淆，成员没有引用会被移除
#keepclasseswithmembers 保留类和类中的成员，防止被混淆或移除，保留指明的成员
#keepclasseswithmembernames 保留类和类中的成员，防止被混淆，保留指明的成员，成员没有引用会被移除

-keep public class * extends com.kotlin.basemvvm.base.BaseApplication{
*;
}
-keep public class * extends com.kotlin.basemvvm.integration.ConfigModule{
*;
}
-keep public class * extends com.kotlin.basemvvm.integration.ManifestParser{
*;
}

#ARouter start
-keep public class com.alibaba.android.arouter.routes.**{*;}
-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}

# 如果使用了 byType 的方式获取 Service，需添加下面规则，保护接口
-keep interface * implements com.alibaba.android.arouter.facade.template.IProvider

# 如果使用了 单类注入，即不定义接口实现 IProvider，需添加下面规则，保护实现
-keep class * implements com.alibaba.android.arouter.facade.template.IProvider{
*;
}
#ARouter end