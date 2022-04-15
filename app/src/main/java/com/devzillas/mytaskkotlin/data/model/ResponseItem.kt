package com.devzillas.mytaskkotlin.data.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.RawValue
import kotlinx.parcelize.Parcelize

data class ResponseItem(
    @SerializedName("address")
    val address: String,
    @SerializedName("address_id")
    val address_id: String,
    @SerializedName("coordinate_mobile")
    val coordinate_mobile: String,
    @SerializedName("coordinate_phone_number")
    val coordinate_phone_number: String,
    @SerializedName("first_name")
    val first_name: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("last_name")
    val last_name: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double,
    @SerializedName("region")
    val region:@RawValue Region,
)