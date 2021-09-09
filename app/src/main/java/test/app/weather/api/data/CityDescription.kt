package test.app.weather.api.data

import java.util.*

class CityDescription {

    private var cityData: LinkedList<CityData> = LinkedList()

    fun setData(){
        cityData.add(CityData("Moscow",55.75F , 37.62F,"GMT+3"))
        cityData.add(CityData("Kaliningrad",54.72F , 20.52F,"GMT+2"))
        cityData.add(CityData("Samara",53.20F , 50.14F,"GMT+4"))
        cityData.add(CityData("Novosibirsk",55.04F , 82.93F,"GMT+7"))
        cityData.add(CityData("Krasnoyarsk",56.02F , 92.87F,"GMT+7"))
        cityData.add(CityData("Irkutsk",52.30F , 104.3F,"GMT+8"))
        cityData.add(CityData("Vladivostok",43.11F , 131.87F,"GMT+10"))
        cityData.add(CityData("London",51.51F , -0.13F,"GMT0"))
        cityData.add(CityData("Berlin",52.52F , 13.41F,"GMT+1"))
        cityData.add(CityData("Paris",48.85F , 2.35F,"GMT+1"))
        cityData.add(CityData("Rome",41.89F , 12.51F,"GMT+1"))
        cityData.add(CityData("Madrid",40.42F , -3.7F,"GMT+1"))
        cityData.add(CityData("Cape Town",-33.92F , 18.42F,"GMT+2"))
        cityData.add(CityData("Tokyo",35.69F , 139.69F,"GMT+9"))
        cityData.add(CityData("Shanghai",31.23F , 121.47F,"GMT+8"))
        cityData.add(CityData("Hong Kong",22.39F , 114.11F,"GMT+8"))
        cityData.add(CityData("Sydney",-33.87F , 151.21F,"GMT+10"))
        cityData.add(CityData("Singapore",1.29F , 103.85F,"GMT+8"))
        cityData.add(CityData("NY",40.71F , -74.00F,"GMT-5"))
        cityData.add(CityData("Chicago",41.88F , -87.63F,"GMT-6"))
        cityData.add(CityData("LA",34.05F , -118.24F,"GMT-8"))
        cityData.add(CityData("Mexico City",19.43F , -99.13F,"GMT-6"))
        cityData.add(CityData("Vancouver",49.28F , -123.12F,"GMT-8"))
        cityData.add(CityData("Sao Paulo",-23.54F , -46.64F,"GMT-3"))
        cityData.add(CityData("Buenos",-34.61F , -58.38F,"GMT-3"))
    }

    fun getCityData() = cityData
}