package test.app.weather.fragments.week

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import test.app.weather.R

class WeekWeatherVH(v: View) : RecyclerView.ViewHolder(v) {
    val dataText: TextView = v.findViewById(R.id.time_textView_recycler_week)
    val imageView: ImageView = v.findViewById(R.id.weather_image_recycler_week)
    val tempMorningView: TextView = v.findViewById(R.id.temp_morning_textView_recycler_week)
    val tempDayView: TextView = v.findViewById(R.id.temp_day_textView_recycler_week)
    val tempEveningView: TextView = v.findViewById(R.id.temp_evening_textView_recycler_week)
    val tempNightView: TextView = v.findViewById(R.id.temp_night_textView_recycler_week)
    val expButton: ImageView = v.findViewById(R.id.exp_recycler_image_view)
    val weekLayout: ConstraintLayout = v.findViewById(R.id.week_weather_layout)
}

class WeekWeatherVHExp(v: View) : RecyclerView.ViewHolder(v) {
    val descriptionText: TextView = v.findViewById(R.id.description_textView_recycler_week)
    val windSpeedText: TextView = v.findViewById(R.id.windSpeed_textView_recycler_week)
    val humidityText: TextView = v.findViewById(R.id.humidity_textView_recycler_week)
    val popText: TextView = v.findViewById(R.id.pop_textView_recycler_week)
    val feelsTempMorningView: TextView = v.findViewById(R.id.feels_temp_morning_textView_recycler_week)
    val feelsTempDayView: TextView = v.findViewById(R.id.feels_temp_day_textView_recycler_week)
    val feelsTempEveningView: TextView = v.findViewById(R.id.feels_temp_evening_textView_recycler_week)
    val feelsTempNightView: TextView = v.findViewById(R.id.feels_temp_night_textView_recycler_week)
}