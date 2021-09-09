package test.app.weather.api.data

import com.google.gson.annotations.SerializedName

data class HourlyData(

    @SerializedName("dt")
    var data_time: Int,

    @SerializedName("sunrise")
    var sunrise: Int,

    @SerializedName("sunset")
    var sunset: Int,

    @SerializedName("temp")
    var temp: Float,

    @SerializedName("feels_like")
    var feels_like: Float,

    @SerializedName("pressure")
    var pressure: Float,

    @SerializedName("humidity")
    var humidity: Float,

    @SerializedName("dew_point")
    var dew_point: Float,

    @SerializedName("uvi")
    var uvi: Float,

    @SerializedName("clouds")
    var clouds: Float,

    @SerializedName("visibility")
    var visibility: Float,

    @SerializedName("wind_speed")
    var wind_speed: Float,

    @SerializedName("wind_deg")
    var wind_deg: Float,

    @SerializedName("pop")
    var pop: Float,

    @SerializedName("weather")
    var weather: List<WeatherData>,

    @SerializedName("rain")
    var rain: RainWaitingTimeData,

    @SerializedName("snow")
    var snow: SnowWaitingTimeData,

    )
