package com.hackaton.pastport.network.model.auth

data class SignUpRequest(
    val username: String,
    val password: String
)