package com.devzillas.mytaskkotlin.data.remote

import com.devzillas.mytaskkotlin.data.model.ClientInformation
import com.devzillas.mytaskkotlin.data.model.PostInfo
import com.devzillas.mytaskkotlin.data.model.ResponseItem
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body

import retrofit2.http.GET
import retrofit2.http.POST


interface GetInformationApi {
    @GET("karfarmas/address")
    suspend fun getInformation(
    ): Response<ArrayList<ResponseItem>>

    @POST("karfarmas/address")
    suspend fun postInformation(
        @Body clientInformation: PostInfo
    ): Response<ResponseItem>


}