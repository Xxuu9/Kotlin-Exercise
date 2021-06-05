package com.example.simplepokedex.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.simplepokedex.R
import com.example.simplepokedex.network.models.PokemonInfo

class PokemonListAdapter(private val clickListener: OnPokemonClickListener): RecyclerView.Adapter<PokemonViewHolder>() {
    var pokemonList: List<PokemonInfo> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemonList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }
}

class PokemonViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

    companion object {
        fun from(parent: ViewGroup): PokemonViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_pokemon_list_item, parent, false)
            return PokemonViewHolder(itemView)
        }
    }

    private val itemTitle: TextView = itemView.findViewById(R.id.item_title)

    fun bind(pokemonInfo: PokemonInfo, clickListener: OnPokemonClickListener) {
        itemTitle.text = pokemonInfo.title
        itemView.setOnClickListener {
            clickListener.onPokemonClick(pokemonInfo)
        }
    }
}

class OnPokemonClickListener(private val clickListener: (pokemonInfo: PokemonInfo) -> Unit) {
    fun onPokemonClick(pokemonInfo: PokemonInfo) = clickListener.invoke(pokemonInfo)
}