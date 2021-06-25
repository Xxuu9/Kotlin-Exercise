package com.kim.deryk.byung.dogviewerapplication.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.kim.deryk.byung.dogviewerapplication.BundleKeys
import com.kim.deryk.byung.dogviewerapplication.DogViewerApplication
import com.kim.deryk.byung.dogviewerapplication.R
import com.kim.deryk.byung.dogviewerapplication.data.DogRepository
import com.kim.deryk.byung.dogviewerapplication.data.network.models.Breed
import com.kim.deryk.byung.dogviewerapplication.data.network.models.BreedDetails

class ImageActivity : AppCompatActivity() {

    private val viewModel by viewModels<ImageViewModel> {
        val apiService = (application as DogViewerApplication).serviceLocator.apiService
        val repository = DogRepository(apiService)
        ImageViewModelFactory(repository, this, intent.extras)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        viewModel.dogBreedDetails.observe(this){
            Log.e("txx3", it.toString())
            populate(it)
        }
    }

    private fun populate(breedDetails: BreedDetails){
        //val dogBreedName: TextView = findViewById(R.id.dog_breed_name)
        //dogBreedName.text = breedDetails.url
        val dogImage: ImageView = findViewById(R.id.dog_image_view)

        Glide.with(this@ImageActivity)
            .load(breedDetails.url)
            .centerInside()
            .into(dogImage)
    }
}

