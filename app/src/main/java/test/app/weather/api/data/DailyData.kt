package test.app.weather.api.data

import com.google.gson.annotations.SerializedName

data class DailyData(

    @SerializedName("dt")
    var data_time: Int,

    @SerializedName("sunrise")
    var sunrise: Int,

    @SerializedName("sunset")
    var sunset: Int,

    @SerializedName("moonrise")
    var moonrise: Int,

    @SerializedName("moonset")
    var moonset: Int,

    @SerializedName("moon_phase")
    var moon_phase: Float,

    @SerializedName("temp")
    var temp: DailyTempData,

    @SerializedName("feels_like")
    var feels_like: FeelsLikeTempData,

    @SerializedName("pressure")
    var pressure: Float,

    @SerializedName("humidity")
    var humidity: Float,

    @SerializedName("dew_point")
    var dew_point: Float,

    @SerializedName("wind_speed")
    var wind_speed: Float,

    @SerializedName("wind_deg")
    var wind_deg: Float,

    @SerializedName("weather")
    var weather: List<WeatherData>,

    @SerializedName("clouds")
    var clouds: Float,

    @SerializedName("uvi")
    var uvi: Float,

    @SerializedName("pop")
    var pop: Float,

    @SerializedName("rain")
    var rain: Float,

    @SerializedName("snow")
    var snow: Float,


)
