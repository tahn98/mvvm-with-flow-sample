package com.example.mvvmwithflowexample.ui.weatherforecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.mvvmwithflowexample.Result
import com.example.mvvmwithflowexample.databinding.FragmentWeatherForecastFlowDataStreamBinding
import com.example.mvvmwithflowexample.viewmodels.WeatherForecastDataStreamFlowViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherForecastDataStreamFlowFragment : Fragment(){
    private lateinit var binding : FragmentWeatherForecastFlowDataStreamBinding
    private val weatherViewModel by viewModel<WeatherForecastDataStreamFlowViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherForecastFlowDataStreamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            weatherViewModel.weatherForecast.collect {
                when (it) {
                    is Result.Loading -> {
                        binding.tvDegree.text = "Loading"
                    }
                    is Result.Success -> {
                        // Update weather data
                        binding.tvDegree.text = it.data.toString()
                    }
                    else -> {
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}