package test.app.weather.mvvm

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import test.app.weather.R
import test.app.weather.recycler.WeatherAdapter

class WeatherFragment : Fragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val weatherInflater = inflater.inflate(R.layout.fragment_weather, container, false)
        val cityTextView: TextView = weatherInflater.findViewById(R.id.city_textView)
        val sunriseTextView: TextView = weatherInflater.findViewById(R.id.sunrise_textView)
        val sunsetTextView: TextView = weatherInflater.findViewById(R.id.sunset_textView)
        val tempTextView: TextView = weatherInflater.findViewById(R.id.temp_textView)
        val feelLikeTextView: TextView = weatherInflater.findViewById(R.id.feelsLike_textView)

        val gifViewModel by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }

        val weatherRecycler: RecyclerView = weatherInflater.findViewById(R.id.weather_recyclerView)

        val weatherAdapter = WeatherAdapter()

        weatherRecycler.layoutManager = LinearLayoutManager(context as AppCompatActivity)
        weatherRecycler.adapter = weatherAdapter

        gifViewModel.getData(55.75F, 37.62F)

        gifViewModel.getWeatherData().observe(viewLifecycleOwner, {
            weatherAdapter.setData(it.hourly)
            cityTextView.text = it.timezone
            sunriseTextView.text = it.current.sunrise.toString()
            sunsetTextView.text = it.current.sunset.toString()
            tempTextView.text = it.current.temp.toString()+ " \u2103"
            feelLikeTextView.text = it.current.feels_like.toString()+" \u2103"
        })

        return weatherInflater
    }
}