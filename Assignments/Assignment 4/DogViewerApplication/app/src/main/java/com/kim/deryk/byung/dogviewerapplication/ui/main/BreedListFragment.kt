package com.kim.deryk.byung.dogviewerapplication.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kim.deryk.byung.dogviewerapplication.BundleKeys
import com.kim.deryk.byung.dogviewerapplication.DogViewerApplication
import com.kim.deryk.byung.dogviewerapplication.data.DogRepository
import com.kim.deryk.byung.dogviewerapplication.data.domain.Breed
import com.kim.deryk.byung.dogviewerapplication.databinding.FragmentBreedListBinding
import com.kim.deryk.byung.dogviewerapplication.ui.details.ImageActivity

class BreedListFragment: Fragment() {

    private val viewModel by viewModels<MainViewModel> {
        val serviceLocator = (requireActivity().application as DogViewerApplication).serviceLocator
        val apiService = serviceLocator.apiService
        val database = serviceLocator.database
        val repository = DogRepository(apiService, database)
        MainViewModelFactory(repository, this, arguments)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBreedListBinding.inflate(inflater, container, false)


        val adapter = BreedListAdapter(object: OnBreedClickListener {
            override fun onBreedClick(breed: Breed) {
                viewModel.onBreedClicked(breed)
            }

            override fun onFaveClick(breed: Breed) {
                viewModel.onFaveClick(breed)
            }
        })

        binding.editText.addTextChangedListener {
            viewModel.onNewSearchKey(it?.toString() ?: "")
        }


        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.loading.isVisible = it
            binding.recyclerView.isVisible = !it
        }

        binding.recyclerView.also {
            it.layoutManager = LinearLayoutManager(activity)
            it.adapter = adapter
        }

        viewModel.breedList.observe(viewLifecycleOwner) {
            //it?.let {
            adapter.submitList(it)
            //}
        }

        viewModel.navigateToDetails.observe(viewLifecycleOwner) {
            it?.let {
                val intent = Intent(activity, ImageActivity::class.java).apply {
                    putExtra(BundleKeys.BREED, it)
                }
                startActivity(intent)
                viewModel.onNavigateToDetailFinished()
            }
        }

        return binding.root
    }

}