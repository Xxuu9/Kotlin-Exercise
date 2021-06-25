package com.kim.deryk.byung.dogviewerapplication.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kim.deryk.byung.dogviewerapplication.BundleKeys
import com.kim.deryk.byung.dogviewerapplication.DogViewerApplication
import com.kim.deryk.byung.dogviewerapplication.R
import com.kim.deryk.byung.dogviewerapplication.data.DogRepository
import com.kim.deryk.byung.dogviewerapplication.data.network.models.Breed
import com.kim.deryk.byung.dogviewerapplication.ui.details.ImageActivity
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

//Name: Xiangxu Teng
//Student #: A00230335

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>{
        val apiService = (application as DogViewerApplication).serviceLocator.apiService
        val repository = DogRepository(apiService)
        MainViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = BreedListAdapter(OnBreedClickListener {
            viewModel.onDogBreedClick(it)

        })
        findViewById<RecyclerView>(R.id.recycler_view).also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
        }

        viewModel.dogBreeds.observe(this){
            adapter.breeds = it
            adapter.notifyDataSetChanged()
        }

        viewModel.navigateToDetails.observe(this){
            it?.let {
                val intent = Intent(this, ImageActivity::class.java).apply{
                    putExtra(BundleKeys.DOG_BREED, it)
                }
                startActivity(intent)
                viewModel.onNavigateToDetailComplete()
            }
        }
    }
}