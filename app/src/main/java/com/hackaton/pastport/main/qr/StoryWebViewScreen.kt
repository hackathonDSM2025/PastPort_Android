package com.hackaton.pastport.main.qr

import android.util.Log
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.viewinterop.AndroidView

private const val BASE_URL = "http://localhost:3000"

@Composable
fun StoryWebViewScreen(
    navToWrite: () -> Unit,
    navToBack: () -> Unit
) {
    var webView: WebView? by remember { mutableStateOf(null) }

    var isFinish by remember { mutableStateOf(false) }
    var currentUrl by remember { mutableStateOf(BASE_URL) }

    LaunchedEffect(isFinish) {
        if (isFinish) {
            navToWrite()
        }
    }

    val onPressBack = {
        if (webView?.canGoBack() == true) {
            webView?.goBack()
        } else {
            navToBack()
        }
    }

    BackHandler {
        onPressBack()
    }

    AndroidView(
        factory = {
            val myWebView = WebView(it)
            myWebView.webViewClient = CustomWebViewClient { url ->
                isFinish = true
            }

            myWebView.settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                mixedContentMode = WebSettings.MIXED_CONTENT_NEVER_ALLOW
                loadsImagesAutomatically = true
                cacheMode = WebSettings.LOAD_DEFAULT
                textZoom = 100
                mediaPlaybackRequiresUserGesture = false
            }

            myWebView.apply {
                webView = this
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
        },
        update = {
            if (it.url.toString().startsWith("$BASE_URL/end")) {
                isFinish = true
            }
            it.loadUrl(currentUrl)
        }
    )
}

class CustomWebViewClient(
    private val onRedirectToFinalUrl: (String) -> Unit
) : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        val url = request?.url.toString()
        return try {
            if (url.startsWith("$BASE_URL/end")) {
                onRedirectToFinalUrl(url)
                true
            } else {
                false
            }
        } catch (e: Exception) {
            Log.e("CustomWebViewClient", e.message.toString())
            false
        }
    }
}