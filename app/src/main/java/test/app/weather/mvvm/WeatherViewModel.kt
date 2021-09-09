package test.app.weather.mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import test.app.weather.api.ApiClientObject
import test.app.weather.api.ApiResponse
import test.app.weather.api.data.CityData
import test.app.weather.api.data.CityDescription
import java.util.*

class WeatherViewModel: ViewModel() {

    private var weatherData: MutableLiveData<ApiResponse> = MutableLiveData()
    private val cityDiscription = CityDescription()

    fun setCities(): LinkedList<CityData> {
        viewModelScope.launch(Dispatchers.IO) {
            cityDiscription.setData()
        }
        return cityDiscription.getCityData()
    }

    fun getWeatherData() = weatherData

    fun getData(lat: Float, lon: Float){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = ApiClientObject.apiClient.getWeatherInCity(
                    lat,
                    lon,
                    "metric",
                    "minutely,alerts",
                    "1b916d803de90495c5dba70b809a0aee"
                )
                weatherData.postValue(data)

                Log.d("API", data.toString())
                Log.d("API", data.hourly.size.toString())

            } catch (e: Throwable){
                Log.e("API", e.toString())
            }
        }
    }
}