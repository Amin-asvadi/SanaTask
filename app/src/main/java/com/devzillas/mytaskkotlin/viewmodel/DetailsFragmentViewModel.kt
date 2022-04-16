package com.devzillas.mytaskkotlin.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devzillas.mytaskkotlin.data.model.ResponseItem
import com.devzillas.mytaskkotlin.repository.InformationRepository
import com.devzillas.mytaskkotlin.utile.NetworkDataState
import com.devzillas.mytaskkotlin.utile.NetworkLinstener
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DetailsFragmentViewModel @Inject constructor(
    private val repository: InformationRepository,
    @ApplicationContext private val context: Context
):ViewModel() {
    val client_information: MutableLiveData<NetworkDataState<ArrayList<ResponseItem>>> = MutableLiveData()


    init {
        getInformation()
    }

    fun getInformation() = viewModelScope.launch {
        safeInformationCall()
    }

    private suspend fun safeInformationCall() {
        client_information.postValue(NetworkDataState.Loading())
        try {
            if (NetworkLinstener.hasInternetConnection(context)) {
                val response = repository.getInformation()
                client_information.postValue(handleBreakingNewsResponse(response))
            } else {
                 client_information.postValue(NetworkDataState.Error("No Internet Connection"))
            }
        } catch (ex: Exception) {
            when (ex) {
                is IOException -> client_information.postValue(NetworkDataState.Error("Network Failure"))
                 else -> client_information.postValue(NetworkDataState.Error("Conversion Error"))
            }
        }
    }


    private fun handleBreakingNewsResponse(response: Response<ArrayList<ResponseItem>>):NetworkDataState<ArrayList<ResponseItem>>  {
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


