package com.kotlin.web.mvvm.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

class X5WebView : WebView {
    private val client: WebViewClient = object : WebViewClient() {
        /**
         * 防止加载网页时调起系统浏览器
         */
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    constructor(arg0: Context?, arg1: AttributeSet?) : super(arg0!!, arg1) {
        this.webViewClient = client
        initWebViewSettings()
    }

    constructor(arg0: Context?) : super(arg0!!) {}

    private fun initWebViewSettings() {
        val webSetting = this.settings
        webSetting.javaScriptCanOpenWindowsAutomatically = true
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE)
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.pluginState = WebSettings.PluginState.ON_DEMAND
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.cacheMode = WebSettings.LOAD_NO_CACHE
        webSetting.cacheMode = WebSettings.LOAD_DEFAULT //根据cache-control决定是否从网络上取数据。
        webSetting.allowFileAccess = true
        webSetting.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
        webSetting.defaultTextEncodingName = "UTF-8"
        webSetting.setSupportZoom(false)
        webSetting.displayZoomControls = false
        webSetting.builtInZoomControls = true
        webSetting.useWideViewPort = true
        webSetting.setSupportMultipleWindows(false)
        webSetting.loadWithOverviewMode = true
        webSetting.setAppCacheEnabled(true)
        webSetting.databaseEnabled = true
        webSetting.domStorageEnabled = true
        webSetting.javaScriptEnabled = true
        webSetting.setGeolocationEnabled(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSetting.mixedContentMode = 2
        }
        webSetting.setAppCachePath(context.filesDir.absolutePath + "cache/")
        webSetting.databasePath = context.filesDir.absolutePath + "cache/"
        webSetting.setGeolocationDatabasePath(context.filesDir.absolutePath + "cache/")
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.pluginState = WebSettings.PluginState.ON_DEMAND
        //         webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
//         webSetting.setPreFectch(true);
//        webSetting.setUserAgentString("$getUserAgentString;it_appua");
        // this.getSettingsExtension().setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);//extension
        // settings 的设计
    }
}