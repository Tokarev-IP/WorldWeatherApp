package test.app.weather.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    //https://api.openweathermap.org/data/2.5/onecall?lat=33.44&lon=-94.04&exclude=minutely&appid=1b916d803de90495c5dba70b809a0aee

    @GET("data/2.5/onecall?")
    suspend fun getWeatherInCity(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float,
        @Query("units") units: String,
        @Query("exclude") part: String,
        @Query("appid") key: String,
    ): ApiResponse
}