package com.example.webapp

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import com.example.webapp.databinding.ActivityMainBinding

const val BASE_URL = "https://www.google.com/"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var connectionState : ConnectionLiveData
    private lateinit var web: WebView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        web = binding.wvMain
        checkNetworkConnection()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkNetworkConnection() {
        connectionState = ConnectionLiveData(application)

        connectionState.observe(this) { isConnected ->
            if (isConnected) {
                onStartBrowsing()
            } 
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(Build.VERSION_CODES.O)
    fun onStartBrowsing(){
        web.webViewClient = WebViewClient()
        web.apply {
            loadUrl(BASE_URL)
            settings.javaScriptEnabled = true
            settings.safeBrowsingEnabled = true
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (web.canGoBack()) {
            web.goBack()
        } else {
            super.onBackPressed()
        }
    }
}