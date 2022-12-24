package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class CustomerModel(val email: String, var password: String)