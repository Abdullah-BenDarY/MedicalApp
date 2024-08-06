package com.example.medicalapp.ui.hr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalapp.data.Data
import com.example.medicalapp.data.ModelAlluser
import com.example.medicalapp.data.UsersData
import com.example.medicalapp.network.NetworkState
import com.example.medicalapp.reposeitory.Repository
import com.example.medicalapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.temporal.TemporalQuery
import javax.inject.Inject
@HiltViewModel
class HrViewModel @Inject constructor( val repository : Repository): ViewModel() {
    private val _createUserLiveData = MutableLiveData<Resource<Data>>()
    private val _mutableUsersLiveData = MutableLiveData<Resource<List<UsersData>?>>()
    private val _filterdList= MutableLiveData<List<UsersData>?>()
    val mutableLiveData get() = _createUserLiveData
    val mutableUsersLiveData get() = _mutableUsersLiveData
    val filterdList get() = _filterdList


    fun filterText(query: String) {
        viewModelScope.launch(IO) {
            val response = repository.filterUsers(query)
            _filterdList.postValue(response)
        }
    }

    fun getUsers(type: String) {
        viewModelScope.launch(IO) {
            try {
                val response = repository.getAllUsers(type)
                if (response.status == 1) {
                    _mutableUsersLiveData.postValue(Resource.Success(response.data))
                } else {
                    _mutableUsersLiveData.postValue(Resource.Error(response.message))
                }
            } catch (e: Exception) {
                _mutableUsersLiveData.postValue(Resource.Error("An error occurred: ${e.message}"))
            }
        }
    }
    fun createNewUser(
        email: String,
        password: String,
        fName: String,
        lName: String,
        gender: String,
        specialist: String,
        birthday: String,
        status: String,
        address: String,
        mobile: String,
        type: String
    ) {
        viewModelScope.launch(IO) {
            try {
                val data = repository.registerNewUser(
                    email,
                    password,
                    fName,
                    lName,
                    gender,
                    specialist,
                    birthday,
                    status,
                    address,
                    mobile,
                    type
                )

                if (data.status == 1) {
                    mutableLiveData.postValue(Resource.Success(data.data))

                } else {
                    mutableLiveData.postValue(Resource.Error(data.message))
                }
            } catch (e: Exception) {
                mutableLiveData.postValue(Resource.Error("An error occurred: ${e.message}"))
            }
        }
    }
}
