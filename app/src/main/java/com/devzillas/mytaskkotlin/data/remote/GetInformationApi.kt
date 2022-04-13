package com.devzillas.mytaskkotlin.data.remote

import com.devzillas.mytaskkotlin.data.model.ClientResponse
import com.devzillas.mytaskkotlin.data.model.ResponseItem
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.Response

import retrofit2.http.GET



interface GetInformationApi {
    @GET("karfarmas/address")
    suspend fun getInformation(
    ):Response<List<ResponseItem>>
}