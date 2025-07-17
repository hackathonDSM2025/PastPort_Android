package com.hackaton.pastport.main.mymedal.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackaton.pastport.main.mymedal.repository.MyMedalRepository
import com.hackaton.pastport.network.model.badge.BadgeListItemData
import com.hackaton.pastport.network.model.user.BadgeData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyMedalViewModel @Inject constructor(
    private val repository: MyMedalRepository
) : ViewModel() {
    var medalList by mutableStateOf<List<BadgeListItemData>>(listOf())
    var selectedMedalDetail by mutableStateOf(medalList[0])

    var isLoading by mutableStateOf(false)

    fun selectedMedalDetail(medal: BadgeListItemData) {
        selectedMedalDetail = medal
    }

    fun getInfo() {
        viewModelScope.launch {
            isLoading = true
            val result = repository.getMyMedal()
            result.onSuccess {
                medalList = it.data.badges
            }
        }
    }
}