package com.kim.deryk.byung.dogviewerapplication.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kim.deryk.byung.dogviewerapplication.R
import com.kim.deryk.byung.dogviewerapplication.data.domain.Breed
import com.kim.deryk.byung.dogviewerapplication.databinding.ViewBreedItemBinding

class BreedListAdapter(private val breedClickListener: OnBreedClickListener): RecyclerView.Adapter<BreedListViewHolder>() {

    var breeds: List<Breed> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedListViewHolder {
        return BreedListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: BreedListViewHolder, position: Int) {
        holder.bind(breeds[position], breedClickListener)
    }

    override fun getItemCount(): Int = breeds.size
}

class BreedListViewHolder private constructor(private val binding: ViewBreedItemBinding): RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): BreedListViewHolder {
            val binding = ViewBreedItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
            return BreedListViewHolder(binding)
        }
    }

    //private val breedNameTextView = itemView.findViewById<TextView>(R.id.breed_name_text_view)

    fun bind(breed: Breed, onBreedClickListener: OnBreedClickListener) {
        binding.breedNameTextView.text = breed.title

        itemView.setOnClickListener {
            onBreedClickListener.onBreedClick(breed)
        }

        binding.faveIcon.setImageResource(
            if (breed.isFaved) R.drawable.ic_favorite_24 else R.drawable.ic_favorite_border_24
        )

        binding.faveIcon.setOnClickListener {
            onBreedClickListener.onFaveIconClick(breed)
        }

    }
}

interface OnBreedClickListener {
    fun onBreedClick(breed: Breed)
    fun onFaveIconClick(breed: Breed)
}