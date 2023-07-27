package com.example.weatherapp.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

enum class SearchApiStatus { LOADING, ERROR, DONE }

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: SearchRepository) : ViewModel() {

    private val _locationsList = MutableLiveData<List<SearchItem>>()
    private val _navigateTo = MutableLiveData<SearchItem?>()
    private val _status = MutableLiveData<SearchApiStatus>()

    val locationsList: LiveData<List<SearchItem>>
        get() = _locationsList

    val navigateTo: LiveData<SearchItem?>
        get() = _navigateTo

    val status: LiveData<SearchApiStatus>
        get() = _status

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

}
