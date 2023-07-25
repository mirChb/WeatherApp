package com.example.weatherapp.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.databinding.FragmentWeatherDetailsBinding

class WeatherDetailsFragment : Fragment() {

    private lateinit var viewModel: WeatherDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = FragmentWeatherDetailsBinding.inflate(inflater)

        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(WeatherDetailsViewModel::class.java)

        val searchItemFrag = WeatherDetailsFragmentArgs.fromBundle(requireArguments()).selectedSearchItem
        viewModel.setSearchItem(searchItemFrag)

        binding.viewModel = viewModel
        binding.gridList.adapter = DetailsAdapter()


        binding.sevenDays.setOnClickListener {
            viewModel.onSevenDaysClicked(searchItemFrag)
        }

        viewModel.navigateToSevenDays.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                this.findNavController().navigate(WeatherDetailsFragmentDirections.actionDetailedWeatherToSevenDays(searchItemFrag))
                viewModel.onSevenDaysNavigationDone()
            }
        })


        return binding.root

    }

    companion object {
        fun newInstance() = WeatherDetailsFragment()
    }

}