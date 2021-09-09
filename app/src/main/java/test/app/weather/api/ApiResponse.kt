package test.app.weather.api

import com.google.gson.annotations.SerializedName
import test.app.weather.api.data.*

data class ApiResponse(

    @SerializedName("lat")
    var lat: Float,

    @SerializedName("lon")
    var lon: Float,

    @SerializedName("timezone")
    var timezone: String,

    @SerializedName("timezone_offset")
    var timezone_offset: Int,

    @SerializedName("current")
    var current: CurrentData,

//    @SerializedName("minutely")
//    var minutely: List<MinutelyData>,

    @SerializedName("hourly")
    var hourly: List<HourlyData>,

    @SerializedName("daily")
    var daily: List<DailyData>,

//    @SerializedName("alerts")
//    var alerts: List<AlertsData>,
)
