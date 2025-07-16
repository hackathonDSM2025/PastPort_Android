package com.hackaton.pastport.network.model

data class BaseResponse<T>(
    val success: Boolean,
    val data: T
)