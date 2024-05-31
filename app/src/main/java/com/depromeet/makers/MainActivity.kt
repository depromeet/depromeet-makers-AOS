package com.depromeet.makers

import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        var isKeepSplash = true
        setContentView(R.layout.activity_main)

        splashScreen.setKeepOnScreenCondition {
            isKeepSplash
        }

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.statusBarColor = Color.parseColor("#e2e8f0")
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = true

        val webView = findViewById<WebView>(R.id.webview)

        val webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                isKeepSplash = false
            }
        }
        webView.webViewClient = webViewClient
        webView.webChromeClient = WebChromeClient()

        webView.settings.apply {
            loadWithOverviewMode = true
            useWideViewPort = true
            setSupportZoom(false)
            javaScriptEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            domStorageEnabled = true
        }

        webView.loadUrl("https://makers.depromeet.com/")
    }
}