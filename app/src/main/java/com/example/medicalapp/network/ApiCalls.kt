package com.example.medicalapp.network

import com.example.medicalapp.data.ModelAlluser
import com.example.medicalapp.data.ModelCallsResponce
import com.example.medicalapp.data.ModelGetAllCalls
import com.example.medicalapp.data.ModelLogin
import com.example.medicalapp.data.ModelNewUser
import com.example.medicalapp.data.ModelResponseCreation
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiCalls {
    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("device_token") deviceToken: String
    ): ModelLogin

    @FormUrlEncoded
    @POST("register")
    suspend fun registerNewUser(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("first_name") fName: String,
        @Field("last_name") lName: String,
        @Field("gender") gender: String,
        @Field("specialist") specialist: String,
        @Field("birthday") birthday: String,
        @Field("status") status: String,
        @Field("address") address: String,
        @Field("mobile") mobile: String,
        @Field("type") type: String
    ): ModelNewUser

    @FormUrlEncoded
    @POST("show-profile")
    suspend fun showProfile(
        @Field("user_id") id :Int
    ):ModelLogin

    @GET("doctors")
    suspend fun getAllUsers(
        @Query("type")
        type :String
    ): ModelAlluser

    // calls
    @FormUrlEncoded
    @POST("calls")
    suspend fun createCall(
        @Field("patient_name") patientName: String,
        @Field("doctor_id") doctorId: Int,
        @Field("age") age: String,
        @Field("phone") phone :String,
        @Field("description") description:String
    ): ModelResponseCreation

    @GET("calls")
    suspend fun getAllCalls():ModelGetAllCalls

    @FormUrlEncoded
    @PUT("calls-accept/{id}")
    suspend fun acceptOrRejectCall(
        @Path("id") id: Int,
        @Field("status") status: String
    ): ModelCallsResponce






}