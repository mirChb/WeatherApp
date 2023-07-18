package com.example.weatherapp.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWeatherDetailsBinding


class WeatherDetailsFragment : Fragment() {

    private lateinit var viewModel: WeatherDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = FragmentWeatherDetailsBinding.inflate(inflater)

        binding.lifecycleOwner = this

        val application = requireNotNull(activity).application
        val searchItem = WeatherDetailsFragmentArgs.fromBundle(requireArguments()).selectedSearchItem
        val isdarkModeEnable = WeatherDetailsFragmentArgs.fromBundle(requireArguments()).isDarkModeEnabled
        Log.i("WeatherDetailsFragment", "isDarkModeEnabled: $isdarkModeEnable")
        val viewModelFactory = WeatherDetailsViewModelFactory(searchItem, application)

        viewModel = ViewModelProvider(this, viewModelFactory).get(WeatherDetailsViewModel::class.java)

        binding.viewModel = viewModel
        binding.gridList.adapter = DetailsAdapter()

        val weatherDetails = WeatherDetailsFragmentArgs.fromBundle(requireArguments()).selectedSearchItem

        viewModel.navigateToSearch.observe(viewLifecycleOwner,
            Observer<Boolean> { shouldNavigate ->
                if (shouldNavigate == true) {
                    val navController = binding.root.findNavController()
                    /////////navController.navigate(R.id.action_weatherDetailsFragment_to_searchFragment)
                    viewModel.onNavigatedToSearch()
                }
            })

        if (isdarkModeEnable) {
            binding.detailsConstLayout.setBackgroundResource(R.drawable.custom_gradient_night)
        } else {
            binding.detailsConstLayout.setBackgroundResource(R.drawable.custom_gradient)
        }

        return binding.root

    }

    companion object {
        fun newInstance() = WeatherDetailsFragment()
    }
}