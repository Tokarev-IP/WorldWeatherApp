package test.app.weather.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import test.app.weather.MyApplication
import test.app.weather.R
import test.app.weather.api.ApiResponse
import test.app.weather.api.data.CurrentData
import test.app.weather.api.data.DailyData
import test.app.weather.api.data.HourlyData
import test.app.weather.api.data.Time
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

@SuppressLint("SimpleDateFormat")
class WeatherAdapter: RecyclerView.Adapter<WeatherVH>() {

    private var weatherData: List<HourlyData> = emptyList()
    private var weatherDaysData: List<DailyData> = emptyList()
    private val dataFormat = SimpleDateFormat("HH:mm dd.MM.yyyy")
    private val dataFormatDays = SimpleDateFormat("dd.MM.yyyy")
    private var time = Time.HOURS
    private var timeZoneString = "GMT+3"
    var layout by Delegates.notNull<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherVH {
        dataFormat.timeZone = TimeZone.getTimeZone(timeZoneString)

        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return WeatherVH(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: WeatherVH, position: Int) {
        if (time == Time.HOURS) {
            holder.dataText.text = dataFormat.format(weatherData[position].data_time * 1000L)
            holder.tempView.text = weatherData[position].temp.toString() + " \u2103"
            holder.windSpeedText.text =
                "Wind speed: " + weatherData[position].wind_speed.toString() + " m/s"
            holder.humidityText.text =
                "Humidity: " + weatherData[position].humidity.toString() + " %"
            holder.popText.text =
                "Precipitation probability: " + weatherData[position].pop.toString()

            Picasso.get()
                .load("http://openweathermap.org/img/wn/${weatherData[position].weather[0].icon}@2x.png")
                .placeholder(R.drawable.ic_baseline_image_search_24)
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(holder.imageView)

        } else {
            holder.dataTextDays.text = dataFormatDays.format(weatherDaysData[position].data_time * 1000L)

            holder.tempMorningView.text ="morning: "+ weatherDaysData[position].temp.morning.toString() + " \u2103"
            holder.tempDayView.text ="day: "+ weatherDaysData[position].temp.day.toString() + " \u2103"
            holder.tempEveningView.text ="evening: "+ weatherDaysData[position].temp.evening.toString() + " \u2103"
            holder.tempNightView.text ="night: "+ weatherDaysData[position].temp.night.toString() + " \u2103"

            holder.windSpeedTextDays.text =
                "Wind speed: " + weatherDaysData[position].wind_speed.toString() + " m/s"
            holder.humidityTextDays.text =
                "Humidity: " + weatherDaysData[position].humidity.toString() + " %"
            holder.popTextDays.text =
                "Precipitation probability: " + weatherDaysData[position].pop.toString()

            Picasso.get()
                .load("http://openweathermap.org/img/wn/${weatherDaysData[position].weather[0].icon}@2x.png")
                .placeholder(R.drawable.ic_baseline_image_search_24)
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(holder.imageViewDays)
        }

    }

    override fun getItemCount(): Int {
        return if (time == Time.HOURS)
            weatherData.size
        else weatherDaysData.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: ApiResponse){
        weatherData = data.hourly
        weatherDaysData = data.daily
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDataTime(timeData: Time){
        time = timeData
        notifyDataSetChanged()
    }
}