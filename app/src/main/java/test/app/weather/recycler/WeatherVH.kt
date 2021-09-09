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

    val dataTextDays: TextView = v.findViewById(R.id.time_textView_recycler_days)
    val imageViewDays: ImageView = v.findViewById(R.id.weather_image_recycler_days)
    val tempMorningView: TextView = v.findViewById(R.id.temp_morning_textView_recycler_days)
    val tempDayView: TextView = v.findViewById(R.id.temp_day_textView_recycler_days)
    val tempEveningView: TextView = v.findViewById(R.id.temp_evening_textView_recycler_days)
    val tempNightView: TextView = v.findViewById(R.id.temp_night_textView_recycler_days)
    val windSpeedTextDays: TextView = v.findViewById(R.id.windSpeed_textView_recycler_days)
    val humidityTextDays: TextView = v.findViewById(R.id.humidity_textView_recycler_days)
    val popTextDays: TextView = v.findViewById(R.id.pop_textView_recycler_days)


}