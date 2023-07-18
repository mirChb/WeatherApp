package com.example.weatherapp.details

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.example.weatherapp.R
import com.example.weatherapp.network.WeatherApi
import com.example.weatherapp.search.SearchApiStatus
import com.example.weatherapp.search.SearchItem
import kotlinx.coroutines.launch
import java.io.IOException
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap

class WeatherDetailsViewModel(searchItem: SearchItem, app: Application): AndroidViewModel(app) {

    val appContext = getApplication<Application>().applicationContext

    private val _status = MutableLiveData<SearchApiStatus>()
    val status: LiveData<SearchApiStatus>
        get() = _status

    private val _selectedSearchItem = MutableLiveData<SearchItem>()
    val selectedSearchItem: LiveData<SearchItem>
        get() = _selectedSearchItem

    private val _properties = MutableLiveData<CurrentWeather>()
    val properties: LiveData<CurrentWeather>
        get() = _properties

    private val _propertiesList = MutableLiveData<List<CardViewLines?>>()
    val propertiesList: LiveData<List<CardViewLines?>>
        get() = _propertiesList

    private val _navigateToSearch = MutableLiveData<Boolean>()
    val navigateToSearch: LiveData<Boolean>
        get() = _navigateToSearch

//    private val _navigateToSearchItem = MutableLiveData<SearchItem?>()
//    val navigateToSearchItem: LiveData<SearchItem?>
//        get() = _navigateToSearchItem

    init {
        _selectedSearchItem.value = searchItem
        selectedSearchItem.value?.let { getWeather(it.name) }
    }

    fun getWeather(q: String) {
        viewModelScope.launch {
            _status.value = SearchApiStatus.LOADING
            try {
                _properties.value = WeatherApi.reftrofitService.getDetails(q).await()
                _status.value = SearchApiStatus.DONE
                show(appContext)
            } catch (e: IOException) {
                _status.value = SearchApiStatus.ERROR
            }
        }
    }

    private fun show(context: Context) {
        val details = arrayListOf<CardViewLines?>()
        details.add(ContextCompat.getDrawable(context, R.drawable.baseline_wb_sunny_24)
            ?.let { CardViewLines(it.toBitmap(), "Sunrise", _properties.value?.forecast?.forecastday?.get(0)?.astro?.sunrise) })
        details.add(ContextCompat.getDrawable(context, R.drawable.baseline_nightlight_24)
            ?.let { CardViewLines(it.toBitmap(), "Sunset", _properties.value?.forecast?.forecastday?.get(0)?.astro?.sunset) })
        details.add(ContextCompat.getDrawable(context, R.drawable.baseline_air_24)
            ?.let { CardViewLines(it.toBitmap(), "Wind", _properties.value?.current?.wind_kph_string) })
        details.add(ContextCompat.getDrawable(context, R.drawable.baseline_vertical_align_center_24)
            ?.let { CardViewLines(it.toBitmap(), "Pressure", _properties.value?.current?.pressure_mb_string) })
        details.add(ContextCompat.getDrawable(context, R.drawable.baseline_water_drop_24)
            ?.let { CardViewLines(it.toBitmap(), "Humidity", _properties.value?.current?.humidity_string) })
        details.add(ContextCompat.getDrawable(context, R.drawable.baseline_info_24)
            ?.let { CardViewLines(it.toBitmap(), "Created By", "Weather Api") })
        _propertiesList.value = details
    }

    fun onNavigatedToSearch() {
        _navigateToSearch.value = false
    }
}