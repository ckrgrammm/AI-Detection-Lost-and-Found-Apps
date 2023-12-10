package com.example.fyps.helpers

import com.google.firebase.firestore.auth.User
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call


interface AdminApiService {
    @POST("/createAdmin")
    fun createAdmin(@Body adminUser: com.example.fyps.model.User): Call<ResponseBody>
}