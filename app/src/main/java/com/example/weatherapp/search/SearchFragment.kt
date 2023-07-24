package com.example.weatherapp.search

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    lateinit var binding: FragmentSearchBinding

    private val viewModel: SearchViewModel by lazy {
        ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    private var darkOrLight = false

    private var isDarkOrLight = context?.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        darkOrLight = (isDarkOrLight==Configuration.UI_MODE_NIGHT_NO)

        binding = FragmentSearchBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.searchBar.addTextChangedListener { onType() }

        setUpObservers()
        setUpClickListeners()

        return binding.root
    }

    private fun onType() {
        val search = binding.searchBar.text
        if (search.isNotEmpty()) {
            viewModel.getProperties(search.toString())
        }
    }

    private fun setUpObservers() {
        viewModel.navigateTo.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                this.findNavController().navigate(SearchFragmentDirections.actionSearchToDetailedWeather(it))
                viewModel.displayDetailsDone()
            }
        })
    }

    private fun setUpClickListeners() {
        binding.floatingBtn.setOnClickListener {
            onFloatingBtnClicked(requireContext())
        }

        binding.locationsList.adapter = SearchAdapter(SearchClickListener {
            viewModel.displayDetails(it)
        })
    }

    private fun onFloatingBtnClicked(context: Context) {
        val uiMode = context.resources.configuration.uiMode
        var isDarkMode = (uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

        isDarkMode = !isDarkMode

        val newNightMode = if (isDarkMode) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }

        AppCompatDelegate.setDefaultNightMode(newNightMode)

    }

}