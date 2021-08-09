package com.kim.deryk.byung.dogviewerapplication.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kim.deryk.byung.dogviewerapplication.R
import com.kim.deryk.byung.dogviewerapplication.data.domain.Breed
import com.kim.deryk.byung.dogviewerapplication.databinding.ViewBreedItemBinding


private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<Breed>() {
    override fun areItemsTheSame(oldItem: Breed, newItem: Breed): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Breed, newItem: Breed): Boolean {
        return oldItem == newItem
    }
}

class BreedListAdapter(private val breedClickListener: OnBreedClickListener): ListAdapter<Breed, BreedListViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedListViewHolder {
        return BreedListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: BreedListViewHolder, position: Int) {
        holder.bind(getItem(position), breedClickListener)
    }

}

class BreedListViewHolder private constructor(private val binding: ViewBreedItemBinding):
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): BreedListViewHolder {
            val binding = ViewBreedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return BreedListViewHolder(binding)
        }
    }

    fun bind(breed: Breed, onBreedClickListener: OnBreedClickListener) {
        binding.breedNameTextView.text = breed.title
        itemView.setOnClickListener {
            onBreedClickListener.onBreedClick(breed)
        }

        binding.breedFaveButton.setImageResource(
            if (breed.isFaved) R.drawable.ic_favorite_24
            else R.drawable.ic_favorite_border_24
        )

        binding.breedFaveButton.setOnClickListener {
            onBreedClickListener.onFaveClick(breed)
        }
    }
}

interface OnBreedClickListener {
    fun onBreedClick(breed: Breed)
    fun onFaveClick(breed: Breed)
}