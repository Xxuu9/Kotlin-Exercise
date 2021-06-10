package com.example.intentexer


import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var imageUrl = "https://images.dog.ceo/breeds/shiba/shiba-19.jpg"

        val openActivityButton = findViewById<Button>(R.id.open_activity)

        openActivityButton.setOnClickListener{
            val intent = Intent(this, ExerciseActivity::class.java).apply{
                putExtra("image_url",imageUrl)
            }
            startActivity(intent)
        }

        val openUrlButton = findViewById<Button>(R.id.open_url)
        openUrlButton.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(imageUrl))
            startActivity(intent)
        }

        val mapLocation = "geo:46.5289148,-80.9430968"
        val openMapButton = findViewById<Button>(R.id.open_map)
        openMapButton.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mapLocation)).apply{
                setPackage("com.google.android.apps.maps")
            }

            try{
                startActivity(intent)
            }catch (e:ActivityNotFoundException){
                //Activity not found
            }
        }

        val shareButton = findViewById<Button>(R.id.share)
        shareButton.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, imageUrl)
            }

            startActivity(Intent.createChooser(intent, "Share"))
        }

    }
}