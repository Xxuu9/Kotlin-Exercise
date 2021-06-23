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
import com.kim.deryk.byung.dogviewerapplication.data.network.models.Breed

class ImageActivity : AppCompatActivity() {

    private val viewModel by viewModels<ImageViewModel> {
        val apiService = (application as DogViewerApplication).serviceLocator.apiService
        val repository = DogRepository(apiService)
        //val dogBreedName = intent.getStringExtra(BundleKeys.DOG_BREED_NAME) ?: ""
        val dogBreed = intent.getSerializableExtra(BundleKeys.DOG_BREED) ?: Breed("", "")
        ImageViewModelFactory(repository, dogBreed as Breed)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        //val dogBreedNameString = intent.getStringExtra(BundleKeys.DOG_BREED_NAME) ?: ""
        //val dogBreed = intent.getStringExtra(BundleKeys.DOG_BREED_NAME) ?: ""

        val dogBreedName: TextView = findViewById(R.id.dog_breed_name)
        //dogBreedName.text = dogBreedNameString
        viewModel.dogBreedDetails.observe(this){
            Log.e("txx3", it.toString())
            dogBreedName.text = it.url
        }
    }
}