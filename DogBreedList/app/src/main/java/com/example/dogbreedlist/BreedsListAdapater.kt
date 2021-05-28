package com.example.dogbreedlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogbreedlist.network.models.Breed


class BreedsListAdapter(private val clickListener: OnBreedsClickListener): RecyclerView.Adapter<BreedsViewHolder>() {
    var breedsList: List<Breed> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedsViewHolder {
        return BreedsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: BreedsViewHolder, position: Int) {
        holder.bind(breedsList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return breedsList.size
    }
}

class BreedsViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

    companion object {
        fun from(parent: ViewGroup): BreedsViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_breeds_list_item, parent, false)
            return BreedsViewHolder(itemView)
        }
    }

    private val itemTitle: TextView = itemView.findViewById(R.id.item_title)

    fun bind(breed: Breed, clickListener: OnBreedsClickListener) {
        itemTitle.text = breed.title
        itemView.setOnClickListener {
            clickListener.onBreedsClick(breed.title)
        }
    }
}

class OnBreedsClickListener(private val clickListener: (Breeds: String) -> Unit) {
    fun onBreedsClick(breeds: String) = clickListener.invoke(breeds)
}