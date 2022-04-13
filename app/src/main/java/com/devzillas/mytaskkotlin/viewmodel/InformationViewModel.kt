package com.devzillas.mytaskkotlin.viewmodel


import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devzillas.mytaskkotlin.data.model.ClientResponse
import com.devzillas.mytaskkotlin.data.model.ResponseItem
import com.devzillas.mytaskkotlin.repository.InformationRepository
import com.devzillas.mytaskkotlin.utile.NetworkLinstener.Companion.hasInternetConnection
import com.devzillas.mytaskkotlin.utile.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class InformationViewModel @Inject constructor(
    private val repository: InformationRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {
    val client_information: MutableLiveData<List<ResponseItem>> = MutableLiveData()

    var clientResponse: ResponseItem? = null

    init {
        getInformation()
    }

    fun getInformation() = viewModelScope.launch {
        safeInformationCall()
    }

    private suspend fun safeInformationCall() {
       // client_information.postValue(Resource.Loading())
        try {
            if (hasInternetConnection(context)) {
                val response = repository.getInformation()
                client_information.postValue(handleBreakingNewsResponse(response))
            } else {
               // client_information.postValue(Resource.Error("No Internet Connection"))
            }
        } catch (ex: Exception) {
            when (ex) {
               // is IOException -> client_information.postValue(Resource.Error("Network Failure"))
              //  else -> client_information.postValue(Resource.Error("Conversion Error"))
            }
        }
    }


    private fun handleBreakingNewsResponse(response: Response<List<ResponseItem>>): List<ResponseItem> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                Log.d("MyTag", "handleBreakingNewsResponse: $resultResponse")
              // return Resource.Success(clientResponse ?: resultResponse)

                return resultResponse
            }
        }
        return listOf()
    }

}


