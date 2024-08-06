package com.example.medicalapp.ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalapp.reposeitory.Repository
import com.example.medicalapp.util.Resource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import com.example.medicalapp.data.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val repository : Repository) : ViewModel () {
    private val _mutableLiveData = MutableLiveData<Resource<UserData>>()
    val mutableLiveData get() = _mutableLiveData


    fun login(email: String, password: String, deviceToken: String) {
        viewModelScope.launch(IO) {
            try {
                val response = repository.login(email, password, deviceToken)
                if (response.status == 1) {
                    mutableLiveData.postValue(Resource.Success(response.data))

                } else {
                    mutableLiveData.postValue(Resource.Error(response.message))
                }
            } catch (e: Exception) {
                mutableLiveData.postValue(Resource.Error("An error occurred: ${e.message}"))
            }
        }
    }
}