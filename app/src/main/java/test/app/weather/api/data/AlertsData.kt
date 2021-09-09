package test.app.weather.api.data

import com.google.gson.annotations.SerializedName

data class AlertsData(

    @SerializedName("sender_name")
    var sender_name: String,

    @SerializedName("event")
    var event: String,

    @SerializedName("start")
    var start: Int,

    @SerializedName("end")
    var end: Int,

    @SerializedName("description")
    var description: String,

    @SerializedName("tags")
    var tags: String,
)
