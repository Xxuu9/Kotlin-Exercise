package com.kim.deryk.byung.dogviewerapplication.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kim.deryk.byung.dogviewerapplication.BundleKeys
import com.kim.deryk.byung.dogviewerapplication.DogViewerApplication
import com.kim.deryk.byung.dogviewerapplication.R
import com.kim.deryk.byung.dogviewerapplication.data.DogRepository
import com.kim.deryk.byung.dogviewerapplication.data.domain.Breed
import com.kim.deryk.byung.dogviewerapplication.databinding.ActivityMainBinding
import com.kim.deryk.byung.dogviewerapplication.ui.details.ImageActivity

//Name: Xiangxu Teng
//Student #: A00230335

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel> {
        val apiService = (application as DogViewerApplication).serviceLocator.apiService
        val breedDatabase = (application as DogViewerApplication).serviceLocator.breedDatabase
        val repository = DogRepository(apiService, breedDatabase)
        MainViewModelFactory(repository)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = BreedListAdapter(object: OnBreedClickListener {
            override fun onBreedClick(breed: Breed) {
                viewModel.onBreedClicked(breed)
            }

            override fun onFaveIconClick(breed: Breed) {
                viewModel.onFaveClick(breed)
            }
        })

        binding.recyclerView.also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
        }

        viewModel.breedList.observe(this) {
            it?.let {
                adapter.breeds = it
                adapter.notifyDataSetChanged()
            }
        }

        viewModel.navigateToDetails.observe(this) {
            it?.let {
                val intent = Intent(this, ImageActivity::class.java).apply {
                    putExtra(BundleKeys.BREED, it)
                }
                startActivity(intent)
                viewModel.onNavigateToDetailFinished()
            }
        }
    }
}