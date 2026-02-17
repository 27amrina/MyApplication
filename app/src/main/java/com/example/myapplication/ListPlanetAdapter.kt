package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListPlanetAdapter(private val listPlanet: ArrayList<Planet>)
    : RecyclerView.Adapter<ListPlanetAdapter.ListViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDesc: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row_planet, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listPlanet.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val planet = listPlanet[position]

        holder.imgPhoto.setImageResource(planet.photo)
        holder.tvName.text = planet.name
        holder.tvDesc.text = planet.description

        // klik ke detail
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClicked(planet)
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    interface OnItemClickListener {
        fun onItemClicked(data: Planet)
    }
}
