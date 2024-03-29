package com.example.medicalapp.reposeitory

import com.example.medicalapp.data.ModelAlluser
import com.example.medicalapp.data.UsersData
import com.example.medicalapp.network.RetroConnection
import retrofit2.http.Field

class Repository {
    private val originalList: ModelAlluser? = null

    suspend fun login(email: String, password: String, deviceToken: String) =
        RetroConnection.api.login(email, password, deviceToken)

    suspend fun registerNewUser(
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
    ) = RetroConnection.api.registerNewUser(
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

    suspend fun showProfile(id: Int)= RetroConnection.api.showProfile(id)

    suspend fun createCall(patientName: String,doctorId: Int,age: String,phone :String,description:String)
            = RetroConnection.api.createCall(patientName, doctorId, age, phone, description)

    suspend fun getAllUsers(type : String)
            = RetroConnection.api.getAllUsers(type)

     fun filterUsers (query : String): List<UsersData> {
        return originalList?.data?.filter {
            it.first_name.contains(query, ignoreCase = true) || it.type.contains(
                query,
                ignoreCase = true
            )
        } ?: emptyList()
    }
}

