package com.example.medicalapp.data

data class ModelDoctorCases(
    val `data`: List<DoctotorCasesData>,
    val message: String,
    val status: Int
)

data class DoctotorCasesData(
    val created_at: String,
    val id: Int,
    val patient_name: String
)