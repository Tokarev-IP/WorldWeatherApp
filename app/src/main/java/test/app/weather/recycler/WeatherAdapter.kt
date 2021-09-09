package test.app.weather.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import test.app.weather.MyApplication
import test.app.weather.R
import test.app.weather.api.data.CurrentData
import test.app.weather.api.data.HourlyData
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
class WeatherAdapter: RecyclerView.Adapter<WeatherVH>() {

    private var weatherData: List<HourlyData> = emptyList()
    private val dataFormat = SimpleDateFormat("HH:mm dd.MM.yyyy")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherVH {
        dataFormat.timeZone = TimeZone.getTimeZone("GMT+3")

        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return WeatherVH(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: WeatherVH, position: Int) {
        holder.dataText.text = dataFormat.format(weatherData[position].data_time*1000L)
        holder.tempView.text = weatherData[position].temp.toString() + " \u2103"
        holder.windSpeedText.text = "Wind speed: " + weatherData[position].wind_speed.toString() + " m/s"
        holder.humidityText.text = "Humidity: " + weatherData[position].humidity.toString() + " %"
        holder.popText.text = "Precipitation probability: " + weatherData[position].pop.toString()

        Picasso.get()
            .load("http://openweathermap.org/img/wn/${weatherData[position].weather[0].icon}@2x.png")
            .placeholder(R.drawable.ic_baseline_image_search_24)
            .error(R.drawable.ic_baseline_error_outline_24)
            .into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return weatherData.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<HourlyData>){
        weatherData = data
        notifyDataSetChanged()
    }
}