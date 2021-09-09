package test.app.weather.mvvm

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import test.app.weather.R
import test.app.weather.api.data.CityData
import test.app.weather.recycler.WeatherAdapter
import test.app.weather.recycler.WeatherVH
import java.text.SimpleDateFormat
import java.util.*

class WeatherFragment : Fragment() {

    private val dataFormat = SimpleDateFormat("HH:mm")
    private lateinit var citiesList: LinkedList<CityData>

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val weatherInflater = inflater.inflate(R.layout.fragment_weather, container, false)

        val cityTextView: TextView = weatherInflater.findViewById(R.id.city_textView)
        val timeTextView: TextView = weatherInflater.findViewById(R.id.time_textView)
        val sunriseTextView: TextView = weatherInflater.findViewById(R.id.sunrise_textView)
        val sunsetTextView: TextView = weatherInflater.findViewById(R.id.sunset_textView)
        val tempTextView: TextView = weatherInflater.findViewById(R.id.temp_textView)
        val feelLikeTextView: TextView = weatherInflater.findViewById(R.id.feelsLike_textView)
        val windSpeedTextView: TextView = weatherInflater.findViewById(R.id.windSpeed_textView)
        val humidityTextView: TextView = weatherInflater.findViewById(R.id.humidity_textView)
        val weatherImageView: ImageView = weatherInflater.findViewById(R.id.weather_imageView)

        val citySpinner: Spinner = weatherInflater.findViewById(R.id.city_spinner)

        val gifViewModel by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }

        val weatherRecycler: RecyclerView = weatherInflater.findViewById(R.id.weather_recyclerView)

        val weatherAdapter = WeatherAdapter()

        weatherRecycler.layoutManager = LinearLayoutManager(context as AppCompatActivity)
        weatherRecycler.adapter = weatherAdapter

        citiesList = gifViewModel.setCities()
        citiesList.addFirst(CityData("Moscow",55.75F , 37.62F,"GMT+3"))

        dataFormat.timeZone = TimeZone.getTimeZone("GMT+3")

        gifViewModel.getData(55.75F, 37.62F)

        gifViewModel.getWeatherData().observe(viewLifecycleOwner, {
            weatherAdapter.setData(it.hourly)

            cityTextView.text = it.timezone
            timeTextView.text = "local time: "+dataFormat.format(it.current.data_time*1000L)
            sunriseTextView.text = "sunrise: "+dataFormat.format(it.current.sunrise*1000L)
            sunsetTextView.text = "sunset: "+dataFormat.format(it.current.sunset*1000L)
            tempTextView.text = it.current.temp.toString()+ " \u2103"
            feelLikeTextView.text = it.current.feels_like.toString()+" \u2103"
            windSpeedTextView.text = it.current.wind_speed.toString()+" m/s"
            humidityTextView.text = it.current.humidity.toString()

            Picasso.get()
                .load("http://openweathermap.org/img/wn/${it.current.weather[0].icon}@2x.png")
                .placeholder(R.drawable.ic_baseline_image_search_24)
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(weatherImageView)
        })

        citySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                gifViewModel.getData(citiesList[position].lat, citiesList[position].lot)
                dataFormat.timeZone = TimeZone.getTimeZone(citiesList[position].gmt)

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        return weatherInflater
    }
}