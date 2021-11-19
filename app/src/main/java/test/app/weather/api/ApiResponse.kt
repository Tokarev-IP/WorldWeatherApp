package test.app.weather.api

import com.google.gson.annotations.SerializedName
import test.app.weather.api.api.data.DailyData
import test.app.weather.api.api.data.HourlyData

data class ApiResponse(

    @SerializedName("lat")
    var lat: Float,

    @SerializedName("lon")
    var lon: Float,

    @SerializedName("timezone")
    var timezone: String,

    @SerializedName("timezone_offset")
    var timezone_offset: Int,

    @SerializedName("hourly")
    var hourly: List<HourlyData>,

    @SerializedName("daily")
    var daily: List<DailyData>,

    )
