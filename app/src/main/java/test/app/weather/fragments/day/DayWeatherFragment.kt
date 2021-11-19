package test.app.weather.fragments.day

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import test.app.weather.R
import test.app.weather.WeatherViewModel
import test.app.weather.dagger.DaggerDaggerComponent
import javax.inject.Inject

class DayWeatherFragment : Fragment() {

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

        return inflater.inflate(R.layout.fragment_day_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherViewModel = ViewModelProvider(requireActivity(), viewModelFactory)[WeatherViewModel::class.java]

        val dayRecycler = view.findViewById<RecyclerView>(R.id.day_weather_recycler_view)
        dayRecycler.layoutManager = LinearLayoutManager(requireContext())
        val dayWeatherAdapter = DayWeatherAdapter()
        dayRecycler.adapter = dayWeatherAdapter

        weatherViewModel.getWeatherData().observe(viewLifecycleOwner, {
            dayWeatherAdapter.update(it)
        })
    }

    companion object {
        fun newInstance(): DayWeatherFragment {
            return DayWeatherFragment()
        }
    }
}