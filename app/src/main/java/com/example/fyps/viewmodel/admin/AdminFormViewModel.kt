package com.example.fyps.viewmodel.admin

import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fyps.firebaseDatabase.FirebaseDb
import com.example.fyps.helpers.AdminApiService
import com.example.fyps.model.Status
import com.example.fyps.resource.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.UUID


class AdminFormViewModel(private val firebaseDatabase: FirebaseDb) : ViewModel() {

    private val _uploadStatus = MutableLiveData<Resource<String>>()
    val uploadStatus: LiveData<Resource<String>> = _uploadStatus

    private val _adminCreationStatus = MutableLiveData<Resource<String>>() // Changed to Resource<String>
    val adminCreationStatus: LiveData<Resource<String>> = _adminCreationStatus


    // Retrofit setup
    private val retrofit = Retrofit.Builder()
        .baseUrl(getBaseUrl())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val adminApiService = retrofit.create(AdminApiService::class.java)


    fun createAdminAccount(firstName: String, lastName: String, email: String, imagePath: String) {
        _adminCreationStatus.postValue(Resource.Loading())

        // Construct the admin user
        val adminUser = com.example.fyps.model.User(firstName, lastName, email, imagePath, Status.ADMINS)

        // Retrofit call to create admin
        adminApiService.createAdmin(adminUser).enqueue(object : retrofit2.Callback<ResponseBody> {
            override fun onResponse(call: retrofit2.Call<ResponseBody>, response: retrofit2.Response<ResponseBody>) {
                if (response.isSuccessful) {
                    _adminCreationStatus.postValue(Resource.Success("Admin created successfully"))
                    sendPasswordResetEmail(email)
                } else {
                    _adminCreationStatus.postValue(Resource.Error("Failed to create admin account"))
                }
            }

            override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {
                _adminCreationStatus.postValue(Resource.Error("Network error: ${t.message}"))
            }
        })
    }

    private fun sendPasswordResetEmail(email: String) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    // Handle failure in sending password reset email
                    Log.e("AdminFormViewModel", "Failed to send password reset email: ${task.exception?.message}")
                }
            }
    }

    fun uploadProfileImage(image: ByteArray, onComplete: (String) -> Unit) {
        val name = UUID.nameUUIDFromBytes(image).toString()
        Log.d("ViewModel", "Uploading image with UUID: $name")
        _uploadStatus.value = Resource.Loading()

        firebaseDatabase.uploadUserProfileImage(image, name).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                task.result?.storage?.downloadUrl?.addOnSuccessListener { uri ->
                    val imageUrl = uri.toString()
                    Log.d("ViewModel", "Upload successful with URL: $imageUrl")
                    _uploadStatus.postValue(Resource.Success(imageUrl))
                    onComplete(imageUrl)
                }?.addOnFailureListener { exception ->
                    Log.e("ViewModel", "Failed to get download URL: ${exception.message}")
                    _uploadStatus.postValue(Resource.Error(exception.message ?: "Failed to get download URL"))
                }
            } else {
                Log.e("ViewModel", "Upload failed: ${task.exception}")
                _uploadStatus.postValue(Resource.Error(task.exception?.message ?: "Image upload failed"))
            }
        }
    }

    fun updateUserImageUrl(userId: String, imageUrl: String) {
        val userRef = FirebaseFirestore.getInstance().collection("users").document(userId)
        userRef.update("imagePath", imageUrl)
            .addOnSuccessListener {
                Log.d("ViewModel", "User image URL updated successfully")
            }
            .addOnFailureListener { exception ->
                Log.e("ViewModel", "Error updating user image URL: ${exception.message}")
            }
    }


    fun isEmulator(): Boolean {
        return (Build.FINGERPRINT.startsWith("google/sdk_gphone_")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion") || Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith(
            "generic"
        )) || "google_sdk" == Build.PRODUCT
    }

    private fun getBaseUrl(): String? {
        return if (isEmulator()) {
            // Base URL for AVD
            "http://10.0.2.2:3000"
        } else {
            // Base URL for physical device
            // Replace with the actual IP of your development machine
            "http://192.168.0.126:3000"
        }
    }


}
