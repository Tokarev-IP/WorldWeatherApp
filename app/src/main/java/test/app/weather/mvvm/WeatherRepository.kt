package test.app.weather.mvvm

import test.app.weather.api.ApiClientObject
import test.app.weather.api.ApiResponse

class WeatherRepository {

    suspend fun getDataFromApi(lat: Float, lon: Float): ApiResponse {
        return ApiClientObject.apiClient.getWeatherInCity(
            lat,
            lon,
            "metric",
            "minutely,alerts",
            "1b916d803de90495c5dba70b809a0aee"
        )
    }
}