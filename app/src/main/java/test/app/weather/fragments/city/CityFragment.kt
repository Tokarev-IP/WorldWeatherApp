package test.app.weather.fragments.city

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomsheet.BottomSheetDialog
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import test.app.weather.R
import test.app.weather.dagger.DaggerDaggerComponent
import test.app.weather.fragments.WeatherFragment
import test.app.weather.WeatherViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CityFragment : Fragment() {

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

        return inflater.inflate(R.layout.fragment_city, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherViewModel =
            ViewModelProvider(requireActivity(), viewModelFactory)[WeatherViewModel::class.java]

        val cityRecycler =
            view.findViewById<RecyclerView>(R.id.cities_bottom_sheet_recycler)
        val myLocationButton = view.findViewById<Button>(R.id.add_city_button)
        val cityEditText = view.findViewById<EditText>(R.id.find_city_edit_text)
        val closeButton = view.findViewById<ImageButton>(R.id.close_city_fragment_button)

        val bottomSheetDialog = BottomSheetDialog(requireContext()).apply {
            setContentView(R.layout.city_delete_bottom_nav)
        }

        val deleteButton = bottomSheetDialog.findViewById<Button>(R.id.delete_button)
        val notDeleteButton = bottomSheetDialog.findViewById<Button>(R.id.not_delete_button)
        val deleteCityText = bottomSheetDialog.findViewById<TextView>(R.id.delete_text_view)

        notDeleteButton?.let {
            it.setOnClickListener {
                bottomSheetDialog.dismiss()
            }
        }

        val cityAdapter = CityAdapter(
            touchCallBack = { lat, lon ->
                Log.d("TOUCH", "touch callback")
                setViewModel(lat, lon)
                closeFragment()
            },
            bottomNavCallBack = { citiesData ->
                bottomSheetDialog.show()
                deleteCityText?.let {
                    it.text = "Does ${citiesData.name} delete?"
                }
                deleteButton?.let {
                    it.setOnClickListener {
                        weatherViewModel.deleteCityFromRoom(citiesData)
                        bottomSheetDialog.dismiss()
                    }
                }
            }
        )

        cityRecycler.layoutManager = LinearLayoutManager(requireContext()).apply {
            stackFromEnd = true
            reverseLayout = true
        }
        cityRecycler.adapter = cityAdapter

        weatherViewModel.getCitiesData().observe(viewLifecycleOwner, { list ->
            cityAdapter.submitList(list)
            if (!list.isNullOrEmpty())
                cityRecycler.smoothScrollToPosition(list.size - 1)
        })

        closeButton.setOnClickListener {
            closeFragment()
        }

        weatherViewModel.getCitiesFromRoom()

        myLocationButton.setOnClickListener {
            getMyLocation()
        }

        val subject = PublishSubject.create<String>()

        cityEditText.doAfterTextChanged { editText ->
            subject.onNext(editText.toString())
        }

        subject
            .filter { str -> !str.isNullOrBlank() }
            .distinctUntilChanged()
            .debounce(1500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ text ->
                Log.d("Subject", text)
                weatherViewModel.getCity(text)
            }, { e ->
                Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_LONG).show()
            }, {})

        weatherViewModel.getCitiesFromRoom()

    }

    private fun setViewModel(lat: Float, lon: Float) {
        weatherViewModel.getWeather(lat, lon)
    }

    private fun closeFragment() {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, WeatherFragment.newInstance())
            .commitAllowingStateLoss()
    }

    private fun getMyLocation() {
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val fusedLocationClient =
                LocationServices.getFusedLocationProviderClient(requireContext())
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location == null) {
                        Toast.makeText(requireContext(), "NO LOCATION", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        setViewModel(
                            location.latitude.toFloat(),
                            location.longitude.toFloat(),
                        )
                        closeFragment()
                    }
                }
        } else requestPermission()
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            101
        )
    }

    companion object {
        fun newInstance(): CityFragment {
            return CityFragment()
        }
    }
}