package com.devzillas.mytaskkotlin.data.model
import android.os.Parcelable
import kotlinx.android.parcel.RawValue
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseItem(
    val address: String,
    val address_id: String,
    val coordinate_mobile: String,
    val coordinate_phone_number: String,
    val first_name: String,
    val gender: String,
    val id: Int,
    val last_name: String,
    val lat: Double,
    val lng: Double,
    val region:@RawValue Region,
) : Parcelable