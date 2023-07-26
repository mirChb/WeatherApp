package com.example.weatherapp.history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.network.WeatherApi
import com.example.weatherapp.search.SearchItem
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Math.min
import java.text.SimpleDateFormat
import java.util.*

class HistoryViewModel: ViewModel() {
    private val _searchItem = MutableLiveData<SearchItem?>()
    val searchItem: LiveData<SearchItem?>
        get() = _searchItem

    fun setSearchItem(item: SearchItem) {
        _searchItem.value = item
        _searchItem.value?.let { getHistory(it.name) }
    }

    private val _properties = MutableLiveData<History>()
    val properties: LiveData<History>
        get() = _properties

    private val _propertiesList = MutableLiveData<List<HistoryCardView?>>()
    val propertiesList: LiveData<List<HistoryCardView?>>
        get() = _propertiesList

    private fun getHistory(filter: String) {
        viewModelScope.launch {
            val details = arrayListOf<HistoryCardView?>()

            Log.i("HistoryViewModel", "in getHistory()")

            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            val cal = Calendar.getInstance()

            // Fetch the previous 12 hours' weather data
            for (i in 0..11) {
                try {
                    // Calculate the date for the previous hour
                    cal.add(Calendar.HOUR_OF_DAY, -1)
                    val date = dateFormat.format(cal.time)

                    Log.i("HistoryViewModel", "date: $date")

                    val response = WeatherApi.reftrofitService.getHistory(filter, date).await()

                    response.forecast?.forecastday?.get(0)?.hour?.get(i)?.let { hourData ->
                        val time = hourData.time
                        val temp_c = hourData.temp
                        val icon = hourData.condition?.icon ?: ""
                        details.add(HistoryCardView(time, temp_c, icon))
                    }
                } catch (e: IOException) {
                    continue
                }
            }

            _propertiesList.value = details
        }
    }

}