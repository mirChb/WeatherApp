package com.example.weatherapp.details

import androidx.lifecycle.*
import com.example.weatherapp.network.WeatherApi
import com.example.weatherapp.search.SearchApiStatus
import com.example.weatherapp.search.SearchItem
import com.example.weatherapp.search.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(private val repository: SearchRepository): ViewModel() {

    private val _searchItem = MutableLiveData<SearchItem?>()
    val searchItem: LiveData<SearchItem?>
        get() = _searchItem

    fun setSearchItem(item: SearchItem) {
        _searchItem.value = item
        _searchItem.value?.let { getWeather(it.name) }
    }

    private val _status = MutableLiveData<SearchApiStatus>()
    val status: LiveData<SearchApiStatus>
        get() = _status

    private val _properties = MutableLiveData<CurrentWeather>()
    val properties: LiveData<CurrentWeather>
        get() = _properties

    private val _propertiesList = MutableLiveData<List<CardViewText?>>()
    val propertiesList: LiveData<List<CardViewText?>>
        get() = _propertiesList

    private val _navigateToSevenDays = MutableLiveData<SearchItem?>()
    val navigateToSevenDays: LiveData<SearchItem?>
        get() = _navigateToSevenDays

    private val _navigateToSearch = MutableLiveData<String?>()
    val navigateToSearch: LiveData<String?>
        get() = _navigateToSearch

    fun onSevenDaysClicked(currentWeather: SearchItem) {
        _navigateToSevenDays.value = currentWeather
    }

    fun onSevenDaysNavigationDone() {
        _navigateToSevenDays.value = null
    }

    fun onSearchNavigationDone() {
        _navigateToSevenDays.value = null
    }

    private val _navigateToHistory = MutableLiveData<SearchItem?>()
    val navigateToHistory: LiveData<SearchItem?>
        get() = _navigateToHistory

    fun onHistoryClicked(currentWeather: SearchItem) {
        _navigateToHistory.value = currentWeather
    }

    fun onHistoryDone() {
        _navigateToHistory.value = null
    }

    fun getWeather(query: String) {
        viewModelScope.launch {
            _status.value = SearchApiStatus.LOADING
            try {
                _properties.value = repository.getDetails(query).await()
                _status.value = SearchApiStatus.DONE
                show()
            } catch (e: IOException) {
                _status.value = SearchApiStatus.ERROR
            }
        }
    }

    private fun show() {
        val details = arrayListOf<CardViewText?>()
        details.add(CardViewText(DrawableDetails.SUNRISE, "Sunrise", _properties.value?.forecast?.forecastday?.get(0)?.astro?.sunrise))
        details.add(CardViewText(DrawableDetails.SUNSET, "Sunset", _properties.value?.forecast?.forecastday?.get(0)?.astro?.sunset))
        details.add(CardViewText(DrawableDetails.WIND, "Wind", _properties.value?.current?.wind_kph_string))
        details.add(CardViewText(DrawableDetails.PRESSURE, "Pressure", _properties.value?.current?.pressure_mb_string))
        details.add(CardViewText(DrawableDetails.HUMIDITY, "Humidity", _properties.value?.current?.humidity_string))
        details.add(CardViewText(DrawableDetails.INFO, "CreatedBy", "Weather Api"))

        _propertiesList.value = details
    }
}

enum class DrawableDetails {SUNRISE, SUNSET, WIND, PRESSURE, HUMIDITY, INFO}