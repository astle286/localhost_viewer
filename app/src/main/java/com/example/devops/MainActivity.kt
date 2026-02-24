package com.example.devops

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    // Use the same prefs name as SettingsActivity
    private val prefs by lazy { getSharedPreferences("settings", MODE_PRIVATE) }

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Bind XML views
        webView = findViewById(R.id.webView)
        val settingsBtn = findViewById<Button>(R.id.settingsBtn)

        // Configure WebView
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()

        // Load saved IP or default
        val ip = prefs.getString("backend_ip", "192.168.1.51:5000")
        webView.loadUrl("http://$ip")

        // Open SettingsActivity
        settingsBtn.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        // Reload with updated IP when returning from Settings
        val ip = prefs.getString("backend_ip", "192.168.1.51:5000")
        webView.loadUrl("http://$ip")
    }
}
