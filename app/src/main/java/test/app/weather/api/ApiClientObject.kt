package test.app.weather.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClientObject {

    const val BASE_URL = "https://api.openweathermap.org"

    val apiClient: ApiInterface by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return@lazy retrofit.create(ApiInterface::class.java)
    }
}