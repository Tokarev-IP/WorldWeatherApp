package test.app.weather

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import test.app.weather.api.ApiRepository
import test.app.weather.api.ApiResponse
import test.app.weather.api.api.data.SearchCityData
import test.app.weather.db.CitiesData
import test.app.weather.db.RoomRepository
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val apiRep: ApiRepository,
    private val roomRep: RoomRepository
) : ViewModel() {

    private var weatherData: MutableLiveData<ApiResponse> = MutableLiveData()
    private var citiesData: MutableLiveData<List<CitiesData>> = MutableLiveData()

    fun getWeatherData() = weatherData
    fun getCitiesData() = citiesData

    fun getWeather(lat: Float, lon: Float) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = apiRep.getDataFromApi(lat, lon)
                weatherData.postValue(data)
            } catch (e: Throwable) {
                Log.e("API", e.toString())
            }
        }
    }

    fun getCity(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = apiRep.getCityFromApi(name)
                setCity(data)
            } catch (e: Throwable) {
                Log.e("API", e.toString())
            }
        }
    }

    private suspend fun setCity(data: SearchCityData) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                insertCityIntoRoom(data)
                val cities = roomRep.getCities()
                citiesData.postValue(cities)

            } catch (e: Throwable) {
                Log.e("API", e.toString())
            }
        }
    }

    fun getCitiesFromRoom() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val cities = roomRep.getCities()
                citiesData.postValue(cities)
            } catch (e: Throwable) {
                Log.e("API", e.toString())
            }
        }
    }

    fun deleteCityFromRoom(data: CitiesData) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                roomRep.deleteCity(data)
                getCitiesFromRoom()
            } catch (e: Throwable) {
                Log.e("API", e.toString())
            }
        }
    }

    private suspend fun insertCityIntoRoom(data: SearchCityData) {
        roomRep.insertCity(data)
    }

}