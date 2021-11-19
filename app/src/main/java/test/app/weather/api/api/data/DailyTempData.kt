package test.app.weather.api.api.data

import com.google.gson.annotations.SerializedName

data class DailyTempData(

    @SerializedName("day")
    var day: Float,

    @SerializedName("night")
    var night: Float,

    @SerializedName("eve")
    var evening: Float,

    @SerializedName("morn")
    var morning: Float,
)
