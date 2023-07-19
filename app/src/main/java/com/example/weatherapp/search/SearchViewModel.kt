package com.example.weatherapp.search

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.R
import com.example.weatherapp.network.WeatherApi
import kotlinx.coroutines.launch
import java.io.IOException

enum class SearchApiStatus { LOADING, ERROR, DONE }

class SearchViewModel: ViewModel() {

    private val repository = SearchRepository(WeatherApi.reftrofitService)

    private val _locationsList = MutableLiveData<List<SearchItem>>()
    private val _navigateTo = MutableLiveData<SearchItem?>()
    private val _status = MutableLiveData<SearchApiStatus>()
    private val _imageViewSrcCompat = MutableLiveData<Int>()
    private val _backgroundResId = MutableLiveData<Int>()
    private var _isDarkModeEnabled = MutableLiveData<Boolean>(false)

    val locationsList: LiveData<List<SearchItem>>
        get() = _locationsList

    val navigateTo: LiveData<SearchItem?>
        get() = _navigateTo

    val status: LiveData<SearchApiStatus>
        get() = _status

    val imageViewSrcCompat: LiveData<Int>
        get() = _imageViewSrcCompat

    val backgroundResId: LiveData<Int>
        get() = _backgroundResId

    val isDarkModeEnabled: LiveData<Boolean>
        get() = _isDarkModeEnabled


    fun getProperties(q: String) {
        viewModelScope.launch {
            _status.value = SearchApiStatus.LOADING
            try {
                _locationsList.value = repository.getProperties(q).await()
                _status.value = SearchApiStatus.DONE
            } catch (e: IOException) {
                _status.value = SearchApiStatus.ERROR
            }
        }
    }

    fun displayDetails(searchItem: SearchItem) {
        _navigateTo.value = searchItem
    }

    fun displayDetailsDone() {
        _navigateTo.value = null
    }


//    fun onFloatingBtnClicked() {
//        _isDarkModeEnabled.value = !isDarkModeEnabled.value!!
//        Log.i("SearchViewModel", "_isDarkModeEnabled: ${_isDarkModeEnabled.value}")
//
//        val newNightMode = if (isDarkModeEnabled.value!!) {
//            AppCompatDelegate.MODE_NIGHT_YES
//        } else {
//            AppCompatDelegate.MODE_NIGHT_NO
//        }
//
//        AppCompatDelegate.setDefaultNightMode(newNightMode)
//
//        if (isDarkModeEnabled.value!!) {
//            _imageViewSrcCompat.value = R.drawable.baseline_dark_mode_24
//            _backgroundResId.value = R.drawable.custom_gradient
//        } else {
//            _imageViewSrcCompat.value = R.drawable.baseline_light_mode_24
//            _backgroundResId.value = R.drawable.custom_gradient_night
//        }
//    }


    fun onFloatingBtnClicked() {
        _isDarkModeEnabled.value = _isDarkModeEnabled.value?.not()

        val newNightMode = if (isDarkModeEnabled.value == true) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }

        AppCompatDelegate.setDefaultNightMode(newNightMode)

        if (_isDarkModeEnabled.value != true) {
            _imageViewSrcCompat.value = R.drawable.baseline_dark_mode_24
            _backgroundResId.value = R.drawable.custom_gradient
        } else {
            _imageViewSrcCompat.value = R.drawable.baseline_light_mode_24
            _backgroundResId.value = R.drawable.custom_gradient_night
        }
    }


}
