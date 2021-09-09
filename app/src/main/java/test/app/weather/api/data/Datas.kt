package test.app.weather.api.data

import com.google.gson.annotations.SerializedName

data class RainWaitingTimeData(

    @SerializedName("1h")
    var one_hour: Float,
)

data class SnowWaitingTimeData(

    @SerializedName("1h")
    var one_hour: Float,
)

data class MinutelyData(

    @SerializedName("dt")
    var data_time: Int,

    @SerializedName("precipitation")
    var precipitation: Float,
)

data class DailyTempData(

    @SerializedName("day")
    var day: Float,

    @SerializedName("min")
    var min: Float,

    @SerializedName("max")
    var max: Float,

    @SerializedName("night")
    var night: Float,

    @SerializedName("eve")
    var evening: Float,

    @SerializedName("morn")
    var morning: Float,
)

data class FeelsLikeTempData(

    @SerializedName("day")
    var day: Float,

    @SerializedName("night")
    var night: Float,

    @SerializedName("eve")
    var evening: Float,

    @SerializedName("morn")
    var morning: Float,
)
