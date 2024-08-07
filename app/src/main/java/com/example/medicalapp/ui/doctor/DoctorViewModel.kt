package com.example.medicalapp.ui.doctor

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalapp.data.CallsData
import com.example.medicalapp.data.ModelCallsResponce
import com.example.medicalapp.reposeitory.Repository
import com.example.medicalapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoctorViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    private val _mutableLiveData = MutableLiveData<Resource<MutableList<CallsData>?>>()
    val mutableLiveData get() = _mutableLiveData

    private val _callStatusLiveData = MutableLiveData<Resource<ModelCallsResponce>?>()
    val callStatusLiveData get() = _callStatusLiveData


    fun getAllCalls() {
        viewModelScope.launch(IO) {
            try {
                val response = repository.getAllCalls()
                if (response.data.isNotEmpty()) {
                    _mutableLiveData.postValue(Resource.Success(response.data.toMutableList()))

                } else {
                    _mutableLiveData.postValue(Resource.Error(response.message))
                }
            } catch (e: Exception) {
                _mutableLiveData.postValue(Resource.Error("An error occurred: ${e.message}"))
            }
        }
    }

    fun getCAllResponce(status: String , id: Int) {
        viewModelScope.launch(IO) {
            try {
                val response = repository.acceptOrRejectCall( status , id)
                if (response.message.isNotEmpty()) {
                    _callStatusLiveData.postValue(Resource.Success(response))

                } else {
                    _callStatusLiveData.postValue(Resource.Error(response.message))
                }
            } catch (e: Exception) {
                _callStatusLiveData.postValue(Resource.Error("An error occurred: ${e.message}"))
            }
        }
    }

}