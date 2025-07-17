package com.hackaton.pastport.main.map.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackaton.pastport.main.map.repository.MapRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MapViewModel @Inject constructor(
    private val repository: MapRepository
) : ViewModel() {
    var keyword by mutableStateOf("")
    var latitude by mutableFloatStateOf(0.0F)
    var longitude by mutableFloatStateOf(0.0F)
    var imageUrl by mutableStateOf("")
    var description by mutableStateOf("")

    var isLoading by mutableStateOf(false)

    fun onKeywordChange(input: String) {
        keyword = input
    }

    fun onSearch() {
        if (keyword.isEmpty()) return
        viewModelScope.launch {
            isLoading = true
            val result = repository.searchHeritage(keyword)
            result.onSuccess {
                latitude = it.data.latitude
                longitude = it.data.longitude
                imageUrl = it.data.imageUrl
                description = it.data.description
            }
            isLoading = false
        }
    }
}