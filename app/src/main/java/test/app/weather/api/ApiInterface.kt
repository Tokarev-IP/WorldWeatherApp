package test.app.weather.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("data/2.5/onecall?")
    suspend fun getWeatherInCity(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float,
        @Query("units") units: String,
        @Query("exclude") part: String,
        @Query("appid") key: String,
    ): ApiResponse
}