package com.devzillas.mytaskkotlin.data.model

data class Name(
    val address: String,
    val coordinate_mobile: String,
    val coordinate_phone_number: String,
    val first_name: String,
    val gender: String,
    val last_name: String,
    val lat: List<Double>,
    val lng: List<Double>,
    val region: Int
)