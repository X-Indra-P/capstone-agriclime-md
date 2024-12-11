package com.capstone.agriclime.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.agriclime.R
import com.capstone.agriclime.network.Plant

class PlantAdapter(private var plantList: List<Plant>) :
    RecyclerView.Adapter<PlantAdapter.PlantViewHolder>() {

    class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val plantImage: ImageView = itemView.findViewById(R.id.plant_image_card)
        val plantName: TextView = itemView.findViewById(R.id.plant_name_tv)
        val scientificName: TextView = itemView.findViewById(R.id.scientific_name_tv)
        val familyName: TextView = itemView.findViewById(R.id.family_name_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.plant_items, parent, false)
        return PlantViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val plant = plantList[position]

        // Debugging data yang diteruskan ke RecyclerView
        Log.d("PlantAdapter", "Binding: ${plant.common_name}, ${plant.image_url}")

        holder.plantName.text = plant.common_name ?: "Unknown"
        holder.scientificName.text = plant.scientific_name ?: "Unknown"
        holder.familyName.text = plant.family_common_name ?: "Unknown"

        Glide.with(holder.itemView.context)
            .load(plant.image_url)
            .override(1000, 300)
            .placeholder(R.drawable.placeholder_image)
            .error(R.drawable.error_image)
            .into(holder.plantImage)
    }


    override fun getItemCount(): Int = plantList.size

    fun updatePlants(newPlantList: List<Plant>) {
        plantList = newPlantList
        notifyDataSetChanged()
    }
}