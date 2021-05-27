package com.example.recyclerviewexample

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExampleAdapter: RecyclerView.Adapter<ExampleViewHolder>() {

    var data: List<String> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
//        val layout = LayoutInflater.from(parent.context).inflate(R.layout.view_recycler_view_item,parent,false)
//        val idTextView:TextView = layout.findViewById(R.id.id_view)
//        idTextView.text = (id++).toString()
//
//        return ExampleViewHolder(layout)
        return ExampleViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun onViewRecycled(holder: ExampleViewHolder) {
        holder.onRecycled()
    }
}

class ExampleViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView){

    companion object{
        private var id = 0
        fun from(parent: ViewGroup): ExampleViewHolder{
            val layout = LayoutInflater.from(parent.context).inflate(R.layout.view_recycler_view_item,parent,false)
            val idTextView:TextView = layout.findViewById(R.id.id_view)
            idTextView.text = (id++).toString()

            return ExampleViewHolder(layout)
        }
    }

    private val dataTextView: TextView = itemView.findViewById(R.id.text_view)

    fun bind(item:String, position: Int){

        dataTextView.text = item
        if ((position + 1) % 10 == 0) {
            itemView.setBackgroundColor(Color.RED)
        }
    }

    fun onRecycled() {
        itemView.background = null
    }
}