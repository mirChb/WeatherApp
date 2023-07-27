package com.example.weatherapp.sevenDaysForecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.details.CurrentWeather
import com.example.weatherapp.search.SearchItem
import com.example.weatherapp.search.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SevenDaysForecastViewModel @Inject constructor(private val repository: SearchRepository): ViewModel() {

    private val _selectedSearchItem = MutableLiveData<SearchItem>()
    val selectedSearchItem: LiveData<SearchItem>
        get() = _selectedSearchItem

    private val _properties = MutableLiveData<CurrentWeather>()
    val properties: LiveData<CurrentWeather>
        get() = _properties

    private val _navigateTo = MutableLiveData<CurrentWeather?>()
    val navigateTo: LiveData<CurrentWeather?>
        get() = _navigateTo

    fun setSearchItem(item: SearchItem) {
        _selectedSearchItem.value = item
        _selectedSearchItem.value?.let { getDaysForecast(it.name) }
    }

    private val _propertiesList = MutableLiveData<List<SevenDaysCardViewText?>>()
    val propertiesList: LiveData<List<SevenDaysCardViewText?>>
        get() = _propertiesList


    fun getDaysForecast(searchItem: String) {
        viewModelScope.launch {
            val details = arrayListOf<SevenDaysCardViewText?>() 

            for (i in 0..2) {
                try {
                    _properties.value = repository.getSevenDaysForecast(searchItem, "7").await()
                    val date = _properties.value!!.forecast.forecastday[i].date
                    val maxtemp_c = _properties.value!!.forecast.forecastday[i].day.maxtemp_c_string
                    val mintemp_c = _properties.value!!.forecast.forecastday[i].day.mintemp_c_string
                    details.add(SevenDaysCardViewText(date, mintemp_c, maxtemp_c))
                } catch (e: IOException) {
                    continue
                }
            }
            _propertiesList.value = details
        }
    }

}