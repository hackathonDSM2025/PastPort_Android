package com.hackaton.pastport.main.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackaton.pastport.main.home.repository.HomeRepository
import com.hackaton.pastport.network.model.user.BadgeData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {
    var visitedCount by mutableIntStateOf(0)
    var reportCount by mutableIntStateOf(0)
    var medalCount by mutableIntStateOf(0)

    var sortedMedalList by mutableStateOf<List<BadgeData>>(listOf())

    var isLoading by mutableStateOf(false)

    fun getInfo() {
        viewModelScope.launch {
            isLoading = true
            val visitResult = async { repository.getVisitedCount() }
            val medalResult = async { repository.getBadges() }
            val reportResult = async { repository.getReviewCount() }

            visitResult.await().onSuccess {
                visitedCount = it.data.visitCount
            }
            medalResult.await().onSuccess {
                sortedMedalList = it.data.badges.sortedByDescending { LocalDate.parse(it.earnedAt) }.take(6)
                medalCount = it.data.badgeCount
            }
            reportResult.await().onSuccess {
                reportCount = it.data.reviewCount
            }
            isLoading = false
        }
    }
}