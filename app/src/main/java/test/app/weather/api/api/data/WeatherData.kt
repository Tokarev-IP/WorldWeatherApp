package test.app.weather.api.api.data

import com.google.gson.annotations.SerializedName

data class WeatherData(

    @SerializedName("id")
    var id: Int,

    @SerializedName("main")
    var main: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("icon")
    var icon: String,
)
