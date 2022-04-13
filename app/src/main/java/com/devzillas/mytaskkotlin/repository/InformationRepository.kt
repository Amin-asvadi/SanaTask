package com.devzillas.mytaskkotlin.repository

import com.devzillas.mytaskkotlin.data.model.ClientResponse
import com.devzillas.mytaskkotlin.data.model.ResponseItem
import com.devzillas.mytaskkotlin.data.remote.GetInformationApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import java.util.ArrayList

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InformationRepository @Inject constructor(
    private val informationApi: GetInformationApi
)  {
    suspend fun getInformation(): Response<List<ResponseItem>> {
        return informationApi.getInformation()
    }
}