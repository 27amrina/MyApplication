package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OtherPlanetAdapter(
    private val items: List<Planet>
) : RecyclerView.Adapter<OtherPlanetAdapter.VH>() {

    interface OnItemClickListener {
        fun onClick(data: Planet)
    }

    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(l: OnItemClickListener) { listener = l }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.img_planet)
        val tv: TextView = itemView.findViewById(R.id.tv_planet)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_planet_small, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val data = items[position]
        holder.img.setImageResource(data.photo)
        holder.tv.text = data.name

        holder.itemView.setOnClickListener {
            listener?.onClick(data)
        }
    }

    override fun getItemCount(): Int = items.size
}
