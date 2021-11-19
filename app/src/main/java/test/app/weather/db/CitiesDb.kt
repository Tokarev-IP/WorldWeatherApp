package test.app.weather.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CitiesData::class], version = 1)
abstract class CitiesDb : RoomDatabase() {

    abstract fun citiesDao(): CitiesDao
}
