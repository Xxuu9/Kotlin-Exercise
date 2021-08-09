package com.kim.deryk.byung.dogviewerapplication.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainViewPager.adapter = MainViewPagerAdapter(this)

        TabLayoutMediator(binding.mainTabLayout, binding.mainViewPager) {tab, position ->
            tab.text = getString(MainTab.values()[position].titleRes)
        }.attach()


    }
}