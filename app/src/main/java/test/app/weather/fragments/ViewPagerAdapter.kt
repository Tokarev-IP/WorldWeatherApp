package test.app.weather.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import test.app.weather.fragments.day.DayWeatherFragment
import test.app.weather.fragments.week.WeekWeatherFragment

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val dayFragment = DayWeatherFragment.newInstance()
    private val weekFragment = WeekWeatherFragment.newInstance()

    override fun getItemCount(): Int {
        return FRAGMENT_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == FIRST)
            dayFragment
        else weekFragment

    }

    companion object {
        private val FRAGMENT_COUNT = 2
        private val FIRST = 0
    }
}
