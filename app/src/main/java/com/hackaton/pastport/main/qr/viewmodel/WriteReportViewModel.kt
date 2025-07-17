package com.hackaton.pastport.main.qr.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackaton.pastport.main.qr.repository.WriteReviewRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class WriteReportViewModel @Inject constructor(
    private val repository: WriteReviewRepository
) : ViewModel() {
    var reviewText by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var isSuccess by mutableStateOf<Boolean?>(null)

    fun onChangeReviewText(input: String) {
        reviewText = input
    }

    fun submitReview() {
        if (reviewText.isEmpty()) return
        if (isLoading) return

        viewModelScope.launch {
            isLoading = true
            val result = repository.writeReport(reviewText)
            result.onSuccess {
                isSuccess = true
            }
        }
    }
}