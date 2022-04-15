package com.devzillas.mytaskkotlin.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devzillas.mytaskkotlin.data.model.Name
import com.devzillas.mytaskkotlin.data.model.PostInfo
import com.devzillas.mytaskkotlin.data.model.ResponseItem
import com.devzillas.mytaskkotlin.repository.InformationRepository
import com.devzillas.mytaskkotlin.utile.NetworkDataState
import com.devzillas.mytaskkotlin.utile.NetworkLinstener
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val repository: InformationRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {
    val client_info: MutableLiveData<NetworkDataState<ResponseItem>> = MutableLiveData()
    val strarg: MutableLiveData<NetworkDataState<ResponseItem>> = MutableLiveData()


    init {
        postInformation()

    }

    fun postInformation() = viewModelScope.launch {
        safeInformationCall()
    }

    private suspend fun safeInformationCall() {
        try {
            if (NetworkLinstener.hasInternetConnection(context)) {
                val lat = mutableListOf(35.7717503)
                val long = mutableListOf(51.3365315)
                var nameObject = Name(
                    "Mashhad Ebadi",
                    "09150284545",
                    "05137335591",
                    "amin",
                    "Male",
                    "asvadi",
                    lat,
                    long,
                    1
                )
                val response = repository.postInformation(PostInfo(nameObject))
                client_info.postValue(handleInformationResponse(response))
            } else {
                client_info.postValue(NetworkDataState.Error("No Internet Connection"))
            }
        } catch (ex: Exception) {
            Log.d("TAGsafeInformationCall", "safeInformationCall:${ex.message} ")
            when (ex) {
                is IOException -> client_info.postValue(NetworkDataState.Error("Network Failure"))
                else -> client_info.postValue(NetworkDataState.Error("Conversion Error"))
            }
        }
    }

    private fun handleInformationResponse(response: Response<ResponseItem>): NetworkDataState<ResponseItem> {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkDataState.Error("Timeout")
            }
            response.isSuccessful -> {

                val client_information = response.body()
                return NetworkDataState.Success(client_information!!)
            }
            else -> {
                return NetworkDataState.Error(response.message())
            }
        }
    }
}