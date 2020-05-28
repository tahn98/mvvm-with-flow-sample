package com.example.mvvmwithflowexample.ui.weatherforecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.mvvmwithflowexample.Result
import com.example.mvvmwithflowexample.databinding.FragmentWeatherForecastDataStreamBinding
import com.example.mvvmwithflowexample.viewmodels.WeatherForecastDataStreamViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherForecastDataStreamFragment : Fragment() {
    private lateinit var binding: FragmentWeatherForecastDataStreamBinding
    private val weatherViewModel: WeatherForecastDataStreamViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherForecastDataStreamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherViewModel.weatherForecast.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Loading -> {
                    binding.tvDegree.text = "Loading"
                }
                is Result.Success -> {
                    binding.tvDegree.text = it.data.toString()
                }
                is Result.Error -> {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}