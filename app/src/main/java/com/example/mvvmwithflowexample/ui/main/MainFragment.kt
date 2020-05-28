package com.example.mvvmwithflowexample.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mvvmwithflowexample.R
import com.example.mvvmwithflowexample.databinding.FragmentMainBinding
import com.example.mvvmwithflowexample.repository.Theme
import com.example.mvvmwithflowexample.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val mainViewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btCheckWeatherOneShot.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_weatherForecastOneShotFragment)
        }

        binding.btCheckWeatherDataStream.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_weatherForecastDataStreamFragment)
        }

        binding.btChangeTheme.setOnClickListener {
            mainViewModel.setTheme(Theme.DARK)
        }

        binding.btCheckWeatherFlowDataStream.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_weatherForecastFlowDataStreamFragment)
        }

        binding.btSearchCity.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_searchCityFragment)
        }
    }
}