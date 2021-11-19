package test.app.weather.fragments.day

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import test.app.weather.R
import test.app.weather.api.ApiResponse
import java.text.SimpleDateFormat
import java.util.*

class DayWeatherAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var apiResponseDataList: List<ApiResponse> = emptyList()

    @SuppressLint("SimpleDateFormat")
    private val dataFormat = SimpleDateFormat("HH:mm")

    @SuppressLint("SimpleDateFormat")
    private val dataFormatTime = SimpleDateFormat("dd MMM")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TIME)
            TimeWeatherVH(
                LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.time_recycler_item, parent, false)
            )
        else
            DayWeatherVH(
                LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.day_weather_recycler_item, parent, false)
            )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val apiResponseData = apiResponseDataList[0]

        if (holder is TimeWeatherVH) {
            val timeFormat = getTimeZone(dataFormatTime)
            holder.timeText.text =
                timeFormat.format(apiResponseData.hourly[position].data_time * 1000L)
        }

        if (holder is DayWeatherVH) {
            val timeFormat = getTimeZone(dataFormat)
            holder.dataText.text =
                timeFormat.format(apiResponseData.hourly[position].data_time * 1000L)
            holder.tempView.text =
                String.format("%.0f", apiResponseData.hourly[position].temp) + " \u2103"
            holder.windSpeedText.text =
                "Wind speed: " + String.format(
                    "%.0f",
                    apiResponseData.hourly[position].wind_speed
                ) + " m/s"
            holder.humidityText.text =
                "Humidity: " + String.format(
                    "%.0f",
                    apiResponseData.hourly[position].humidity
                ) + " %"
            holder.popText.text =
                "Precipitation probability: " + String.format(
                    "%.0f",
                    apiResponseData.hourly[position].pop * 100F
                ) + " %"
            holder.feelsLike.text =
                "Feels like: " + String.format(
                    "%.0f",
                    apiResponseData.hourly[position].feelsLike
                ) + " \u2103"

            Picasso.get()
                .load("http://openweathermap.org/img/wn/${apiResponseData.hourly[position].weather[0].icon}@2x.png")
                .placeholder(R.drawable.ic_baseline_image_search_24)
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(holder.imageView)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val timeFormat = getTimeZone(dataFormatTime)
        val apiResponseData = apiResponseDataList[0]

        return if (position == 0 ||
            timeFormat.format(apiResponseData.hourly[position].data_time * 1000L) != timeFormat.format(
                apiResponseData.hourly[position - 1].data_time * 1000L
            )
        ) {
            TIME
        } else
            NO_TIME
    }

    override fun getItemCount(): Int {
        return if (apiResponseDataList.isNotEmpty())
            apiResponseDataList[0].hourly.size
        else ZERO
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(data: ApiResponse) {
        apiResponseDataList = listOf(data)
        notifyDataSetChanged()
    }

    private fun getTimeZone(dataFormat: SimpleDateFormat): SimpleDateFormat {
        val apiResponseData = apiResponseDataList[0]
        val gmt = apiResponseData.timezone_offset / 60 / 60
        return if (gmt > 0) {
            dataFormat.timeZone = TimeZone.getTimeZone("GMT+${gmt}")
            dataFormat
        } else {
            dataFormat.timeZone = TimeZone.getTimeZone("GMT${gmt}")
            dataFormat
        }
    }

    companion object {
        private const val ZERO = 0
        private const val TIME = 1
        private const val NO_TIME = 0
    }

}