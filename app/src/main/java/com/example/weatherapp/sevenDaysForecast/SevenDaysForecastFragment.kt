package com.example.weatherapp.sevenDaysForecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.databinding.FragmentSevenDaysForecastBinding

class SevenDaysForecastFragment :  Fragment() {

    private lateinit var viewModel: SevenDaysForecastViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                savedInstanceState: Bundle?): View? {

        val binding = FragmentSevenDaysForecastBinding.inflate(inflater)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(SevenDaysForecastViewModel::class.java)

        val searchItemFrag = SevenDaysForecastFragmentArgs.fromBundle(requireArguments()).selectedSearchItem
        viewModel.setSearchItem(searchItemFrag)

        binding.viewModel = viewModel
        binding.sevenDaysGridList.adapter = SevenDaysAdapter()

        return binding.root

    }

}