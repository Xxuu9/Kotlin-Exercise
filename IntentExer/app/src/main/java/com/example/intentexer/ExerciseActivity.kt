package com.example.intentexer

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class ExerciseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        val imageUrl = intent.getStringExtra("image_url")

        val webView = findViewById<WebView>(R.id.web_view)
        imageUrl?.let{
            webView.loadUrl(it)
        }

    }
}