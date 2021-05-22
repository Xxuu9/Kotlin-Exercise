package com.example.simplepokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

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

                val serviceLocator = (application as SimplePokeDexApplication).serviceLocator
                val apiService = serviceLocator.apiService

                val result = try {
                    apiService.getPokemons()
                } catch (e: HttpException) {
                    null
                } catch (e: IOException) {
                    null
                } catch (e: Exception) {
                    null
                }

                resultText.text = result?.results?.toString()

            }
        }
    }
}