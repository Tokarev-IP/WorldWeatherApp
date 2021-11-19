package test.app.weather.fragments.day

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import test.app.weather.R

open class DayWeatherVH(v: View) : RecyclerView.ViewHolder(v) {
    val dataText: TextView = v.findViewById(R.id.time_textView_recycler_day)
    val imageView: ImageView = v.findViewById(R.id.weather_image_recycler_day)
    val tempView: TextView = v.findViewById(R.id.temp_textView_recycler_day)
    val windSpeedText: TextView = v.findViewById(R.id.windSpeed_textView_recycler_day)
    val humidityText: TextView = v.findViewById(R.id.humidity_textView_recycler_day)
    val popText: TextView = v.findViewById(R.id.pop_textView_recycler_day)
    val feelsLike: TextView = v.findViewById(R.id.feels_like_textView_recycler_day)
}

class TimeWeatherVH(v: View) : DayWeatherVH(v) {
    val timeText: TextView = v.findViewById(R.id.time_text_view)
}
