package test.app.weather.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class CitiesData(

    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "lat")
    val lat: Float,

    @ColumnInfo(name = "lon")
    val lon: Float,

    @ColumnInfo(name = "country")
    val country: String,

    @ColumnInfo(name = "timezone")
    val timezone: Int,
)