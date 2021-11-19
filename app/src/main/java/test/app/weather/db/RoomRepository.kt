package test.app.weather.db

import test.app.weather.api.api.data.SearchCityData
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val db: CitiesDb,
) {

    suspend fun getCities(): List<CitiesData> {
        return db.citiesDao().getCitiesFromRoom()
    }

    suspend fun insertCity(data: SearchCityData) {
        db.citiesDao().insertCityIntoRoom(
            CitiesData(
                data.name,
                data.coordinates.lat,
                data.coordinates.lon,
                data.sys.country,
                data.timeZone
            )
        )
    }

    suspend fun deleteCity(data: CitiesData) {
        db.citiesDao().deleteCityFromRoom(
            data
        )
    }
}