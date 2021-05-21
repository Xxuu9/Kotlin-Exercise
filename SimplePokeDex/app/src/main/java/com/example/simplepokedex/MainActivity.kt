package com.example.simplepokedex

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val resultText: TextView = findViewById(R.id.result_text)
        val mainButton: Button = findViewById(R.id.main_button)

        mainButton.setOnClickListener{
            lifecycleScope.launch{
                mainButton.isEnabled = false
                resultText.text = "Loading the result..."
                sleep()
                resultText.text = "You got the result!"
            }

        }

    }

    suspend fun sleep() {
        withContext(Dispatchers.IO){
            Thread.sleep(3000)
        }
    }



}