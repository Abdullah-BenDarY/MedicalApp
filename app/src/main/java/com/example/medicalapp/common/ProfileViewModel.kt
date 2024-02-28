package com.example.medicalapp.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalapp.data.Data
import com.example.medicalapp.data.UserData
import com.example.medicalapp.data.UsersData
import com.example.medicalapp.reposeitory.Repository
import com.example.medicalapp.util.Resource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
class ProfileViewModel  : ViewModel(){
    private val repository = Repository()
    private val _mutableLiveData = MutableLiveData<Resource<UserData>>()
    val mutableLiveData get() = _mutableLiveData

    fun getUser(id:Int){
        viewModelScope.launch (IO){
            try {
                val user = repository.showProfile(id)
                if (user.status == 1){
                    _mutableLiveData.postValue(Resource.Success(user.data))
                }else{
                    _mutableLiveData.postValue(Resource.Error(user.message))
                }
            }catch (e:Exception){
                _mutableLiveData.postValue(Resource.Error("An error occurred: ${e.message}"))
            }
        }
    }
}