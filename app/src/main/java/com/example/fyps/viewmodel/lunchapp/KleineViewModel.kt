package com.example.fyps.viewmodel.lunchapp

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fyps.firebaseDatabase.FirebaseDb
import com.example.fyps.model.User
import com.example.fyps.resource.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import androidx.lifecycle.LiveData
import com.example.fyps.model.Status
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot


class KleineViewModel(
    private val firebaseDatabase: FirebaseDb
) : ViewModel() {



    private val _currentUser = MutableLiveData<User>()
    val currentUser: LiveData<User> = _currentUser

    private val usersCollectionRef = FirebaseFirestore.getInstance().collection("users")

    val saveUserInformationGoogleSignIn = MutableLiveData<Resource<String>>()
    val register = MutableLiveData<Resource<User>>()



    val login = MutableLiveData<Boolean>()
    val loginError = MutableLiveData<String>()

    val resetPassword = MutableLiveData<Resource<String>>()

    fun registerNewUser(user: User, password: String) {
        register.postValue(Resource.Loading())
        firebaseDatabase.createNewUser(user.email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                firebaseDatabase.saveUserInformation(Firebase.auth.currentUser!!.uid, user) { exception ->
                    if (exception == null) {
                        register.postValue(Resource.Success(user))
                    } else {
                        register.postValue(Resource.Error(exception.toString()))
                    }
                }
            } else {
                task.exception?.let { register.postValue(Resource.Error(it.toString())) }
            }
        }
    }


    private fun saveUserInformationGoogleSignIn(userUid: String, user: User) {
        firebaseDatabase.checkUserByEmail(user.email) { error, isAccountExisted ->
            if (error != null) {
                saveUserInformationGoogleSignIn.postValue(Resource.Error(error))
            } else {
                if (isAccountExisted!!) {
                    saveUserInformationGoogleSignIn.postValue(Resource.Success(userUid))
                } else {
                    firebaseDatabase.saveUserInformation(userUid, user) { exception ->
                        if (exception == null) {
                            saveUserInformationGoogleSignIn.postValue(Resource.Success(userUid))
                        } else {
                            saveUserInformationGoogleSignIn.postValue(Resource.Error(exception.toString()))
                        }
                    }
                }
            }
        }
    }



    fun loginUser(
        email: String,
        password: String
    ) = firebaseDatabase.loginUser(email, password).addOnCompleteListener {
        if (it.isSuccessful)
            login.postValue(true)
        else
            loginError.postValue(it.exception.toString())
    }

    fun resetPassword(email: String) {
        resetPassword.postValue(Resource.Loading())
        firebaseDatabase.resetPassword(email).addOnCompleteListener {
            if (it.isSuccessful)
                resetPassword.postValue(Resource.Success(email))
            else
                resetPassword.postValue(Resource.Error(it.exception.toString()))

        }
    }

    fun signInWithGoogle(idToken: String) {
        saveUserInformationGoogleSignIn.postValue(Resource.Loading())
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseDatabase.signInWithGoogle(credential).addOnCompleteListener { task ->

            if (task.isSuccessful) {
                val userFirebase = FirebaseAuth.getInstance().currentUser
                val fullNameArray = userFirebase!!.displayName?.split(" ")
                val firstName = fullNameArray!![0]
                val size = fullNameArray.size
                var secondName = ""
                if (size == 1)
                    secondName = ""
                else
                    secondName = fullNameArray[1]

                val user = User(firstName, secondName, userFirebase.email.toString(), "")
                saveUserInformationGoogleSignIn(userFirebase.uid, user)
            } else
                saveUserInformationGoogleSignIn.postValue(Resource.Error(task.exception.toString()))


        }
    }

    fun logOut(){
        firebaseDatabase.logout()
    }

    fun isUserSignedIn() : Boolean {
        if (FirebaseAuth.getInstance().currentUser?.uid != null)
            return true
        return false

    }

    fun getUser(userId: String): Task<DocumentSnapshot> {
        return usersCollectionRef.document(userId).get()
    }

    fun getUserStatus(userId: String, callback: (String?) -> Unit) {
        usersCollectionRef.document(userId).get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val status = document.getString("status") // Assuming the field is named "status"
                    callback(status)
                } else {
                    Log.d(TAG, "No such document")
                    callback(null)
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
                callback(null)
            }
    }



    fun updateUserRole(userId: String, newRole: Status): Task<Void> {
        return usersCollectionRef.document(userId).update("status", newRole.name)
    }








}