package test.app.weather.fragments.week

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
import androidx.recyclerview.widget.ConcatAdapter

class WeekWeatherFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        DaggerDaggerComponent
            .factory()
            .create(requireContext())
            .inject(this)

        return inflater.inflate(R.layout.fragment_week_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val weatherViewModel: WeatherViewModel =
            ViewModelProvider(requireActivity(), viewModelFactory)[WeatherViewModel::class.java]

        val arrayListAdapter = mutableListOf<WeekWeatherAdapter>()

        val concatAdapterConfig = ConcatAdapter.Config.Builder()
            .setIsolateViewTypes(false)
            .build()

        val dayRecycler = view.findViewById<RecyclerView>(R.id.week_weather_recycler_view)

        dayRecycler.layoutManager = LinearLayoutManager(requireContext())

        weatherViewModel.getWeatherData().observe(viewLifecycleOwner, { apiResponse ->
            for (data in apiResponse.daily) {
                val weekAdapter = WeekWeatherAdapter(data)
                arrayListAdapter.add(weekAdapter)
            }
            val concatAdapter = ConcatAdapter(concatAdapterConfig, arrayListAdapter)
            dayRecycler.adapter = concatAdapter
        })
    }

    companion object {
        fun newInstance(): WeekWeatherFragment {
            return WeekWeatherFragment()
        }
    }
}