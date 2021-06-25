package com.kim.deryk.byung.dogviewerapplication.ui.details

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.dogBreedDetails.observe(this){
            Log.e("txx3", it.toString())
            populate(it)
        }

        viewModel.share.observe(this) {
            it?.let {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, it.url)
                }
                startActivity(Intent.createChooser(intent, getString(R.string.share)))
                viewModel.onShareComplete()
            }
        }

        viewModel.browse.observe(this) {
            it?.let {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.url))
                try {
                    startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(this, "No application found", Toast.LENGTH_SHORT).show()
                }
                viewModel.onBrowseClickComplete()
            }
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.share -> {
                viewModel.onShareClick()
                true
            }
            R.id.open_in_browser -> {
                viewModel.onBrowseClick()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

