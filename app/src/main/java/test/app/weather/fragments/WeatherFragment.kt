package test.app.weather.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import test.app.weather.R
import test.app.weather.WeatherViewModel
import test.app.weather.dagger.DaggerDaggerComponent
import test.app.weather.fragments.city.CityFragment
import javax.inject.Inject

class WeatherFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        DaggerDaggerComponent
            .factory()
            .create(requireContext())
            .inject(this)

        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherViewModel = ViewModelProvider(requireActivity(), viewModelFactory)[WeatherViewModel::class.java]

        val viewPager: ViewPager2 = view.findViewById(R.id.view_pager)
        val tabLayout: TabLayout = view.findViewById(R.id.tab_layout)
        val cityName: TextView = view.findViewById(R.id.city_name_text_view)
        val progressBar: ProgressBar = view.findViewById(R.id.progress_bar)
        val pagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        val tabs: List<String> = listOf("Day", "Week")

        progressBar.visibility = View.VISIBLE

        viewPager.adapter = pagerAdapter

        val cityFragmentButton: ImageButton =
            view.findViewById(R.id.change_city_button)

        weatherViewModel.getWeatherData().observe(viewLifecycleOwner, {
            progressBar.visibility = View.INVISIBLE
            cityName.text = "time zone: " + it.timezone
        })

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabs[position]
        }.attach()

        cityFragmentButton.setOnClickListener {
            goToCityFragment()
        }
    }

    private fun goToCityFragment(){
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, CityFragment.newInstance())
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    companion object {
        fun newInstance(): WeatherFragment {
            return WeatherFragment()
        }
    }
}