package test.app.weather.api.api.data

import com.google.gson.annotations.SerializedName

data class DailyData(

    @SerializedName("dt")
    var data_time: Int,

    @SerializedName("temp")
    var temp: DailyTempData,

    @SerializedName("feels_like")
    var feelsLike: DailyTempData,

    @SerializedName("humidity")
    var humidity: Float,

    @SerializedName("wind_speed")
    var wind_speed: Float,

    @SerializedName("weather")
    var weather: List<WeatherData>,

    @SerializedName("pop")
    var pop: Float,

    )
