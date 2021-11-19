package test.app.weather.fragments.city

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import test.app.weather.R
import test.app.weather.db.CitiesData

class CityAdapter(
    private val touchCallBack: (Float, Float) -> Unit,
    private val bottomNavCallBack: (CitiesData) -> Unit
) :
    ListAdapter<CitiesData, CityAdapter.BottomSheetVH>(CityDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomSheetVH {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.city_recycler_item, parent, false)
        return BottomSheetVH(view)
    }

    override fun onBindViewHolder(holder: BottomSheetVH, position: Int) {
        holder.name.text = getItem(position).name
        holder.country.text = getItem(position).country

        holder.itemLayout.setOnClickListener {
            touchCallBack(
                getItem(position).lat,
                getItem(position).lon,
            )
        }

        holder.itemLayout.setOnLongClickListener {
            bottomNavCallBack(getItem(position))
            return@setOnLongClickListener true
        }
    }

    inner class BottomSheetVH(v: View) : RecyclerView.ViewHolder(v) {
        val name: TextView = v.findViewById(R.id.city_name_text)
        val country: TextView = v.findViewById(R.id.country_text_view)
        val itemLayout: LinearLayout = v.findViewById(R.id.city_recycler_layout)
    }
}