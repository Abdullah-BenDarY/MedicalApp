package com.example.medicalapp.data

data class ModelAlluser(


    val `data`: List<UsersData>,
    val message: String,
    val status: Int
)

data class UsersData(
    val avatar: String,
    val first_name: String,
    val type : String,
    val id: Int
)
