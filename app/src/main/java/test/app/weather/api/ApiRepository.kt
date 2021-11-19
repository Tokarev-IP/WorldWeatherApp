package test.app.weather.api

import android.util.Log
import test.app.weather.api.api.data.SearchCityData
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val api: ApiInterface,
) {
//763e854706442f07dcb11478c6f0f5d1
//1b916d803de90495c5dba70b809a0aee

    suspend fun getDataFromApi(lat: Float, lon: Float): ApiResponse {
    Log.d("API", "get data from api")
        return api.getWeatherInCity(
            lat,
            lon,
            "metric",
            "minutely,alerts",
            "0b0c80ba3c9b684f1f6deabaff215195"
        )
    }

    suspend fun getCityFromApi(name: String): SearchCityData {
        return api.getCityCoordinates(
            name,
            "0b0c80ba3c9b684f1f6deabaff215195"
        )
    }

}