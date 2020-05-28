package com.example.mvvmwithflowexample.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.mvvmwithflowexample.databinding.FragmentSearchCityBinding
import com.example.mvvmwithflowexample.viewmodels.SearchCityViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchCityFragment : Fragment() {

    private lateinit var binding : FragmentSearchCityBinding
    private lateinit var adapter : CityAdapter
    private val searchCityViewModel by viewModel<SearchCityViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchCityBinding.inflate(inflater, container, false)

        initView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeFilteredCities()
    }

    private fun initView() {
        binding.etCity.doAfterTextChanged {
            val key = it.toString()

            // Set loading indicator
            binding.pbLoading.show()

            // Offer the current text to channel
            searchCityViewModel.cityFilterChannel.offer(key)
        }

        adapter = CityAdapter(searchCityViewModel.cityList)
        binding.rvCity.adapter = adapter
    }

    private fun observeFilteredCities() {
        lifecycleScope.launchWhenStarted {
            searchCityViewModel.cityFilterFlow.collect { filteredCities ->
                // Hide the progress bar
                binding.pbLoading.hide()

                // Set filtered items
                adapter.setItems(filteredCities)
            }
        }
    }
}