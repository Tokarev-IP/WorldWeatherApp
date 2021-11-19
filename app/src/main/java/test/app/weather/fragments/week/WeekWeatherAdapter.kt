package test.app.weather.fragments.week

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import test.app.weather.R
import test.app.weather.api.api.data.DailyData
import java.text.SimpleDateFormat

class WeekWeatherAdapter(private val data: DailyData) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var isExpanded = false

    @SuppressLint("SimpleDateFormat")
    private val dataFormatDays = SimpleDateFormat("dd MMM yyyy")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("CREATE", viewType.toString())
        return if (viewType == ONE)
            WeekWeatherVH(
                LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.week_weather_recycler_item, parent, false)
            )
        else WeekWeatherVHExp(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.week_recycler_view_exp, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is WeekWeatherVH) {

            holder.weekLayout.setOnClickListener {
                isExpanded = !isExpanded
                if (isExpanded) {
                    notifyItemInserted(1)
                    holder.expButton.animate().setDuration(300).rotation(180f)
                } else {
                    notifyItemRemoved(1)
                    holder.expButton.animate().setDuration(300).rotation(0f)
                }
            }

            holder.dataText.text =
                dataFormatDays.format(data.data_time * 1000L)

            holder.tempMorningView.text =
                "morning: " + String.format(
                    "%.0f",
                    data.temp.morning
                ) + " \u2103"

            holder.tempDayView.text =
                "day: " + String.format("%.0f", data.temp.day) + " \u2103"

            holder.tempEveningView.text =
                "evening: " + String.format(
                    "%.0f",
                    data.temp.evening
                ) + " \u2103"

            holder.tempNightView.text =
                "night: " + String.format(
                    "%.0f",
                    data.temp.night
                ) + " \u2103"

            Picasso.get()
                .load("http://openweathermap.org/img/wn/${data.weather[0].icon}@2x.png")
                .placeholder(R.drawable.ic_baseline_image_search_24)
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(holder.imageView)
        }

        if (holder is WeekWeatherVHExp) {
            holder.descriptionText.text = data.weather[0].description

            holder.windSpeedText.text =
                "Wind speed: " + String.format(
                    "%.0f",
                    data.wind_speed
                ) + " m/s"

            holder.humidityText.text =
                "Humidity: " + String.format(
                    "%.0f",
                    data.humidity
                ) + " %"

            holder.popText.text =
                "Precipitation probability: " + String.format(
                    "%.0f",
                    data.pop * 100F
                ) + " %"

            holder.feelsTempMorningView.text =
                "morning: " + String.format(
                    "%.0f",
                    data.feelsLike.morning
                ) + " \u2103"

            holder.feelsTempDayView.text =
                "day: " + String.format("%.0f", data.feelsLike.day) + " \u2103"

            holder.feelsTempEveningView.text =
                "evening: " + String.format(
                    "%.0f",
                    data.feelsLike.evening
                ) + " \u2103"

            holder.feelsTempNightView.text =
                "night: " + String.format(
                    "%.0f",
                    data.feelsLike.night
                ) + " \u2103"
        }
    }

    override fun getItemCount(): Int {
        return if (isExpanded)
            TWO
        else ONE
    }

    override fun getItemViewType(position: Int): Int {
        return if (isExpanded && position != 0)
            TWO
        else ONE
    }

    companion object {
        private const val ONE = 1
        private const val TWO = 2
    }

}