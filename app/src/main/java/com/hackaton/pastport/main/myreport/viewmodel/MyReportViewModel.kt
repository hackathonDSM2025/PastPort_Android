package com.hackaton.pastport.main.myreport.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackaton.pastport.main.myreport.repository.MyReportRepository
import com.hackaton.pastport.network.model.user.ReviewListItemData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyReportViewModel @Inject constructor(
    private val repository: MyReportRepository
) : ViewModel() {
    var reportList by mutableStateOf<List<ReviewListItemData>>(listOf())
    var isLoading by mutableStateOf(false)

     fun getInfo() {
        viewModelScope.launch {
            isLoading = true
            val result = repository.getReportList()
            result.onSuccess {
                reportList = it.data.reviews
            }
            isLoading = false
        }
    }
}