package test.app.weather.api.api.data

import com.google.gson.annotations.SerializedName

data class HourlyData(

    @SerializedName("dt")
    var data_time: Int,

    @SerializedName("temp")
    var temp: Float,

    @SerializedName("feels_like")
    var feelsLike: Float,

    @SerializedName("humidity")
    var humidity: Float,

    @SerializedName("wind_speed")
    var wind_speed: Float,

    @SerializedName("pop")
    var pop: Float,

    @SerializedName("weather")
    var weather: List<WeatherData>,

    )
