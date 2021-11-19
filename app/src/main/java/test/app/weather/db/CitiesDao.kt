package test.app.weather.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CitiesDao {

    @Query("SELECT * FROM cities")
    suspend fun getCitiesFromRoom(): List<CitiesData>

    @Insert
    suspend fun insertCityIntoRoom(city: CitiesData)

    @Delete
    suspend fun deleteCityFromRoom(city: CitiesData)

}