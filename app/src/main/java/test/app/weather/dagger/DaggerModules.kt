package test.app.weather.dagger

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import test.app.weather.api.ApiInterface
import test.app.weather.db.CitiesDb

@Module
object DaggerModules {

    @Provides
    fun getApiClient(): ApiInterface {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

    @Provides
    fun getDatabase(context: Context): CitiesDb {
        return Room.databaseBuilder(
            context.applicationContext,
            CitiesDb::class.java,
            "cities_database"
        ).build()
    }

}