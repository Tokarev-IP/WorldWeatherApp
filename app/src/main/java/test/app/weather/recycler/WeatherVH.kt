package test.app.weather.recycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import test.app.weather.R

class WeatherVH(v: View): RecyclerView.ViewHolder(v) {
    val dataText: TextView = v.findViewById(R.id.time_textView_recycler)
    val imageView: ImageView = v.findViewById(R.id.weather_image_recycler)
    val tempView: TextView = v.findViewById(R.id.temp_textView_recycler)
    val windSpeedText: TextView = v.findViewById(R.id.windSpeed_textView_recycler)
    val humidityText: TextView = v.findViewById(R.id.humidity_textView_recycler)
    val popText: TextView = v.findViewById(R.id.pop_textView_recycler)
}