package test.app.weather.api

import retrofit2.http.GET
import retrofit2.http.Query
import test.app.weather.api.api.data.SearchCityData

interface ApiInterface {

    @GET("data/2.5/onecall?")
    suspend fun getWeatherInCity(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float,
        @Query("units") units: String,
        @Query("exclude") part: String,
        @Query("appid") key: String,
    ): ApiResponse

    @GET("data/2.5/weather?")
    suspend fun getCityCoordinates(
        @Query("q") name: String,
        @Query("appid") key: String,
    ): SearchCityData
}