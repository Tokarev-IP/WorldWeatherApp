package test.app.weather.dagger

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import test.app.weather.MainActivity
import test.app.weather.dagger.viewmodel.ViewModelModule
import test.app.weather.fragments.WeatherFragment
import test.app.weather.fragments.city.CityFragment
import test.app.weather.fragments.day.DayWeatherFragment
import test.app.weather.fragments.week.WeekWeatherFragment

@Component(
    modules = [
        DaggerModules::class,
        ViewModelModule::class,
    ]
)

interface DaggerComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): DaggerComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: WeatherFragment)
    fun inject(fragment: DayWeatherFragment)
    fun inject(fragment: WeekWeatherFragment)
    fun inject(fragment: CityFragment)
}