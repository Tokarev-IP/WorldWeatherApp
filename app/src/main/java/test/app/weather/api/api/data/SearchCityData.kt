package test.app.weather.api.api.data

import com.google.gson.annotations.SerializedName

data class SearchCityData(

    @SerializedName("coord")
    var coordinates: Coordinates,

    @SerializedName("sys")
    var sys: Sys,

    @SerializedName("timezone")
    var timeZone: Int,

    @SerializedName("name")
    var name: String,

    )

data class Coordinates(
    var lat: Float,
    var lon: Float,
)

data class Sys(
    var type: Int,
    var id: Int,
    var country: String,
    var sunrise: Int,
    var sunset: Int,
)
