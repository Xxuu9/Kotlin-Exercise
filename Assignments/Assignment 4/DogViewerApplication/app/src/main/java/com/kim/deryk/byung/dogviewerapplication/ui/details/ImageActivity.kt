package com.kim.deryk.byung.dogviewerapplication.ui.details

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.kim.deryk.byung.dogviewerapplication.DogViewerApplication
import com.kim.deryk.byung.dogviewerapplication.R
import com.kim.deryk.byung.dogviewerapplication.data.DogRepository
import com.kim.deryk.byung.dogviewerapplication.databinding.ActivityImageBinding

class ImageActivity : AppCompatActivity() {
    private val viewModel by viewModels<ImageViewModel> {
        val serviceLocator = (application as DogViewerApplication).serviceLocator
        val apiService = serviceLocator.apiService
        val database = serviceLocator.database
        val repository = DogRepository(apiService, database)
        ImageViewModelFactory(repository, this, intent.extras)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.imageUrl.observe(this) {
            it?.let {
                Glide.with(this).load(it).centerInside().into(binding.dogImageView)
            }
        }


        viewModel.isLoading.observe(this) {isLoading ->
                binding.loading.isVisible = isLoading
                binding.dogImageView.isVisible = !isLoading
        }

        viewModel.shareImage.observe(this) {
            it?.let {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, it)
                }
                startActivity(Intent.createChooser(intent, getString(R.string.share)))
                viewModel.onShareComplete()
            }
        }

        viewModel.browseImage.observe(this) {
            it?.let {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                try {
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {

                }
                viewModel.onBrowseComplete()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.image_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.browse -> {
                viewModel.onBrowseClick()
                true
            }
            R.id.share -> {
                viewModel.share()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}