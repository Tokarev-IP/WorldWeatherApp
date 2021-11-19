package test.app.weather.fragments.city

import androidx.recyclerview.widget.DiffUtil
import test.app.weather.db.CitiesData

class CityDiffUtil: DiffUtil.ItemCallback<CitiesData>() {

    override fun areItemsTheSame(oldItem: CitiesData, newItem: CitiesData): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: CitiesData, newItem: CitiesData): Boolean {
        return oldItem.name == newItem.name
    }
}