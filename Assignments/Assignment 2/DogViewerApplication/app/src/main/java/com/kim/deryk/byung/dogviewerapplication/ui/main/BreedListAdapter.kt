package com.kim.deryk.byung.dogviewerapplication.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kim.deryk.byung.dogviewerapplication.R
import com.kim.deryk.byung.dogviewerapplication.data.network.models.Breed

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

class BreedListViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

    companion object {
        fun from(parent: ViewGroup): BreedListViewHolder {
            val layout = LayoutInflater.from(parent.context).inflate(R.layout.view_breed_item, parent, false)
            return BreedListViewHolder(layout)
        }
    }

    private val breedNameTextView = itemView.findViewById<TextView>(R.id.breed_name_text_view)

    fun bind(breed: Breed, onBreedClickListener: OnBreedClickListener) {
        breedNameTextView.text = breed.title

        itemView.setOnClickListener {
            onBreedClickListener.onBreedClick(breed)
        }
    }
}

class OnBreedClickListener(private val listener: (Breed) -> Unit) {
    fun onBreedClick(breed: Breed) = listener.invoke(breed)
}