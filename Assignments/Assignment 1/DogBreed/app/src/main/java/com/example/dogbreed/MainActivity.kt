package com.example.dogbreed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogbreed.network.models.BreedList
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

//Name: Xiangxu Teng  Student Number: A00230335
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.breeds_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = BreedsListAdapter(OnBreedsClickListener { breeds ->
            Toast.makeText(this, breeds.toString(), Toast.LENGTH_SHORT).show()
        })
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            //Log.e("txx", ">>>>1")
            val serviceLocator = (application as SimpleDogBreedsApplication).serviceLocator
            val apiService = serviceLocator.apiService

            val result: BreedList? = try {
                //Log.e("txx", ">>>>2")
                // Get data from https://dog.ceo/api/breeds/list/all
                apiService.getDogs()
            } catch (e: HttpException) {
                //Log.e("txx", "3 HttpException")
                null
            } catch (e: IOException) {
                //Log.e("txx", "4 IOException")
                null
            } catch (e: Exception) {
                //Log.e("txx", "5 Exception")
                null
            }
            // result can be null or BreedList
            result?.let {
                //Log.e("txx", result.message.toString())
                adapter.breedsList = it.getBreeds()  // BreedList.getBreeds()

                adapter.notifyDataSetChanged()
            }
        }
    }
}