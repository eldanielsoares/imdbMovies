package com.enjoy.imdbjetpackcompose

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enjoy.imdbjetpackcompose.data.SeriesDetails
import com.enjoy.imdbjetpackcompose.data.SeriesService

import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(
    private val seriesService: SeriesService
): ViewModel() {
    private val _seriesData = MutableLiveData<List<SeriesDetails>>()
    val seriesData: LiveData<List<SeriesDetails>>
    get() = _seriesData

    fun getSeries(){
        viewModelScope.launch {
            try {
                val series = seriesService.getSeries()
               _seriesData.value = series.result
            }catch (e: Exception){
                Log.d("Error Serie", e.toString())
            }
        }
    }
}