package com.example.mvvmexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    //the developer is certain that the variable will not be null when accessing it
    //private lateinit var viewModel: MainViewModel

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // when the activity is recreated, it wil use the same viewmodel class as the one before
        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val resultText: TextView = findViewById(R.id.result_text)

        //it will be called everytime there is a change in resultLiveData
        viewModel.resultLiveData.observe(this){
            resultText.text = it
        }

    }
}