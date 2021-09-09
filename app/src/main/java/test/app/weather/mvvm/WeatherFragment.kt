package test.app.weather.mvvm

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.location.LocationManagerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
import test.app.weather.MyApplication
import test.app.weather.R
import test.app.weather.api.data.CityData
import test.app.weather.api.data.Time
import test.app.weather.recycler.WeatherAdapter
import test.app.weather.recycler.WeatherVH
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class WeatherFragment : Fragment() {

    @SuppressLint("SimpleDateFormat")
    private val dataFormat = SimpleDateFormat("HH:mm")
    private lateinit var citiesList: LinkedList<CityData>
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        checkPermisson()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context as AppCompatActivity)

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location->
                if (location == null) {
                    Log.d("LOCATION", "null")
                }
                if (location != null) {
                    Log.d("LOCATION", location.latitude.toString())
                }
            }


        if (ContextCompat.checkSelfPermission(
                context as AppCompatActivity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
//            fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
//                Log.d("LOCATION", task.result.latitude.toString())
            }

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
        val hoursDaysTextView: TextView = weatherInflater.findViewById(R.id.hours_days_textView)

        val hoursButton: Button = weatherInflater.findViewById(R.id.hour_button)
        val daysButton: Button = weatherInflater.findViewById(R.id.day_button)

        val citySpinner: Spinner = weatherInflater.findViewById(R.id.city_spinner)

        val weatherViewModel by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }

        val weatherRecycler: RecyclerView = weatherInflater.findViewById(R.id.weather_recyclerView)
        val weatherAdapter = WeatherAdapter()
        weatherRecycler.layoutManager = LinearLayoutManager(context as AppCompatActivity)
        weatherRecycler.adapter = weatherAdapter

        citiesList = weatherViewModel.setCities()
        citiesList.addFirst(CityData("Moscow",55.75F , 37.62F,"GMT+3"))
        hoursDaysTextView.text = "TODAY"

        hoursButton.setOnClickListener {
            weatherRecycler.adapter = weatherAdapter
            weatherViewModel.setTime(Time.HOURS)
            weatherAdapter.setDataTime(weatherViewModel.getTime())
            hoursDaysTextView.text = "TODAY"
        }
        daysButton.setOnClickListener {
            weatherRecycler.adapter = weatherAdapter
            weatherViewModel.setTime(Time.DAYS)
            weatherAdapter.setDataTime(weatherViewModel.getTime())
            hoursDaysTextView.text = "WEEK"
        }

        dataFormat.timeZone = TimeZone.getTimeZone("GMT+3")

        weatherViewModel.getData(55.75F, 37.62F)

        weatherViewModel.getWeatherData().observe(viewLifecycleOwner, {
            weatherAdapter.setData(it)

            cityTextView.text = it.timezone
            timeTextView.text = "local time: "+dataFormat.format(it.current.data_time*1000L)
            sunriseTextView.text = "sunrise: "+dataFormat.format(it.current.sunrise*1000L)
            sunsetTextView.text = "sunset: "+dataFormat.format(it.current.sunset*1000L)
            tempTextView.text = it.current.temp.toString()+ " \u2103"
            feelLikeTextView.text = "feels like: "+it.current.feels_like.toString()+" \u2103"
            windSpeedTextView.text = "wind speed: "+it.current.wind_speed.toString()+" m/s"
            humidityTextView.text = "humidity: "+it.current.humidity.toString()

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
                weatherViewModel.getData(citiesList[position].lat, citiesList[position].lot)
                dataFormat.timeZone = TimeZone.getTimeZone(citiesList[position].gmt)

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        return weatherInflater
    }

    fun checkPermisson(){
        if (ContextCompat.checkSelfPermission(
                context as AppCompatActivity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                context as AppCompatActivity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )
        }
    }
}