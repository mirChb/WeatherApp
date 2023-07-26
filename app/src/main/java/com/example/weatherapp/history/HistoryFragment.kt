package com.example.weatherapp.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {
    private lateinit var viewModel: HistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHistoryBinding.inflate(inflater)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)

        val searchItemFrag =
            HistoryFragmentArgs.fromBundle(requireArguments()).selectedSearchItem
        viewModel.setSearchItem(searchItemFrag)

        binding.viewModel = viewModel
        binding.historyGridList.adapter = HistoryAdapter()

        return binding.root

    }
}