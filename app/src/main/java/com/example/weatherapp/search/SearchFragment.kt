package com.example.weatherapp.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentSearchBinding
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    lateinit var binding: FragmentSearchBinding

    private val viewModel: SearchViewModel by lazy {
        ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    private var darkOrLight = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentSearchBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        binding.searchBar.addTextChangedListener { onType() }

        binding.locationsList.adapter = SearchAdapter(SearchClickListener {
            viewModel.displayDetails(it)
        })

        viewModel.imageViewSrcCompat.observe(viewLifecycleOwner, Observer { drawableResId ->
            binding.floatingBtn.setImageResource(drawableResId)
        })

        viewModel.backgroundResId.observe(viewLifecycleOwner, Observer { resId ->
            binding.searchConstraintId.setBackgroundResource(resId)
        })

//        binding.floatingBtn.setOnClickListener {
//            viewModel.onFloatingBtnClicked()
//        }

        viewModel.isDarkModeEnabled.observe(viewLifecycleOwner, Observer { isDarkModeEnabled ->
            darkOrLight = isDarkModeEnabled
        })


        viewModel.navigateTo.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                this.findNavController().navigate(SearchFragmentDirections.actionSearchToDetailedWeather(it, darkOrLight))
                viewModel.displayDetailsDone()
            }
        })

        return binding.root
    }

    private fun onType() {
        val search = binding.searchBar.text
        if (search.isNotEmpty()) {
            viewModel.getProperties(search.toString())
        }
    }

}