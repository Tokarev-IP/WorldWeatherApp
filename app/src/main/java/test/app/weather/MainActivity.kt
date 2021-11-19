package test.app.weather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import test.app.weather.fragments.city.CityFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, CityFragment.newInstance())
                .commitAllowingStateLoss()
    }
}