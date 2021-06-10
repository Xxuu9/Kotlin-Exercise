package com.example.intentexer

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class ExerciseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        loadImage(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let {
            loadImage(intent)
        }
    }

    private fun loadImage(intent: Intent) {
        val action = intent.action
        val data = intent.dataString

        val imageUrl = if (action == Intent.ACTION_VIEW && data != null) data
        else intent.getStringExtra("image_url")

        val webView = findViewById<WebView>(R.id.web_view)
        imageUrl?.let {
            webView.loadUrl(it)
        }
    }
}