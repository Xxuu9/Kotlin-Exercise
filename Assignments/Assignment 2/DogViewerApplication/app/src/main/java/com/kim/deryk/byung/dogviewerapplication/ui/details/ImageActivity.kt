package com.kim.deryk.byung.dogviewerapplication.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import com.kim.deryk.byung.dogviewerapplication.BundleKeys
import com.kim.deryk.byung.dogviewerapplication.DogViewerApplication
import com.kim.deryk.byung.dogviewerapplication.R
import com.kim.deryk.byung.dogviewerapplication.data.DogRepository

class ImageActivity : AppCompatActivity() {

    private val viewModel by viewModels<ImageViewModel> {
        val apiService = (application as DogViewerApplication).serviceLocator.apiService
        val repository = DogRepository(apiService)
        val dogBreedName = intent.getStringExtra(BundleKeys.DOG_BREED_NAME) ?: ""
        ImageViewModelFactory(repository, dogBreedName)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        val dogBreedNameString = intent.getStringExtra(BundleKeys.DOG_BREED_NAME) ?: ""

        val dogBreedName: TextView = findViewById(R.id.dog_breed_name)
        Log.e("txx3", dogBreedNameString)
        //dogBreedName.text = dogBreedNameString
        viewModel.dogBreedDetails.observe(this){
            Log.e("txx3", it.toString())
            dogBreedName.text = dogBreedNameString
        }
    }
}