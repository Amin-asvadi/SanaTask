package com.devzillas.mytaskkotlin.repository

import com.devzillas.mytaskkotlin.data.model.PostInfo
import com.devzillas.mytaskkotlin.data.model.ResponseItem
import com.devzillas.mytaskkotlin.data.remote.GetInformationApi
import okhttp3.RequestBody
import retrofit2.Response
import java.util.ArrayList

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InformationRepository @Inject constructor(
    private val informationApi: GetInformationApi
)  {
    suspend fun postInformation(information :PostInfo):Response<ResponseItem>{
        return informationApi.postInformation(information)
    }
    suspend fun getInformation(): Response<ArrayList<ResponseItem>> {
        return informationApi.getInformation()
    }
}