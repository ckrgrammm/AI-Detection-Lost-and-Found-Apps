package com.example.fyps.viewmodel.material

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fyps.model.CourseDocument
import com.example.fyps.model.Material
import com.example.fyps.model.MaterialData
import com.example.fyps.model.MaterialEngageData
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class MaterialViewModel : ViewModel() {
    val materialEngageData = MutableLiveData<MaterialEngageData?>()
    private val _materialList = MutableLiveData<List<MaterialData>>()
    val materialList: LiveData<List<MaterialData>> = _materialList


    private val storageRef = FirebaseStorage.getInstance().reference
    private val db = FirebaseFirestore.getInstance()

    fun fetchMaterialsData() {
        val db = FirebaseFirestore.getInstance()
        db.collection("Materials")
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }

                val tempList = ArrayList<MaterialData>()
                for (document in snapshots!!) {
                    val id = document.id
                    val materialName = document.getString("name") ?: ""
                    val description = document.getString("desc") ?: ""
                    val requirement = document.getString("requirement") ?: ""
                    val rating = document.getDouble("rating") ?: 0.0
                    val imageUrl = document.getString("imageUrl") ?: ""
                    val status = document.getString("status") ?: ""
                    tempList.add(MaterialData(id, materialName, description, requirement, rating, imageUrl, status))
                }
                _materialList.value = tempList
            }
    }
    fun fetchMaterialsEngageData(documentId: String) {
        val db = FirebaseFirestore.getInstance()
        db.collection("Materials").document(documentId)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val materialOverview = MaterialEngageData(
                        document.getString("name") ?: "",
                        document.getLong("view") ?: 0,
                        document.getLong("enroll") ?: 0,
                        document.getLong("graduate") ?: 0,
                        document.getString("imageUrl") ?: ""
                    )
                    materialEngageData.postValue(materialOverview)
                }
            }

    }

    fun fetchMaterialsByCategory(category: String) {
        db.collection("Materials")
            .whereEqualTo("category", category)
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }

                val tempList = ArrayList<MaterialData>()
                for (document in snapshots!!) {
                    val id = document.id
                    val materialName = document.getString("name") ?: ""
                    val description = document.getString("desc") ?: ""
                    val rating = document.getDouble("rating") ?: 0.0
                    val imageUrl = document.getString("imageUrl") ?: ""
                    val status = document.getString("status") ?: ""

                    // Create a MaterialData object
                    val materialData = MaterialData(id, materialName, description, category, rating, imageUrl, status)
                    tempList.add(materialData)
                }

                // Update the LiveData with the list of materials
                _materialList.postValue(tempList)
            }
    }


    fun updateMaterial(material: Material, imageUri: Uri?) {
        // Check if material.id is valid
        if (material.id.isNullOrEmpty()) {
            Log.e(TAG, "Cannot update material: Invalid ID")
            // You might want to invoke some error handling callback here
            return
        }

        val materialRef = db.collection("Materials").document(material.id)
        Log.d(TAG, "Updating material with ID: ${material.id}")

        val uploadTasks = mutableListOf<Task<*>>()

        imageUri?.let { uri ->
            Log.d(TAG, "Image URI for upload: $uri")
            val imageRef = storageRef.child("images/${material.id}")
            uploadTasks.add(imageRef.putFile(uri).continueWithTask {
                imageRef.downloadUrl
            }.addOnSuccessListener { url ->
                Log.d(TAG, "Image uploaded successfully: $url")
                material.imageUrl = url.toString()
            }.addOnFailureListener { e ->
                Log.e(TAG, "Error uploading image: $e")
            })
        } ?: Log.d(TAG, "No image URI provided")

        // Prepare a map with only the fields you want to update
        val updatedFields: Map<String, Any> = mapOf(
            "name" to material.name,
            "desc" to material.desc,
            "category" to material.category,
            "venue" to material.venue,
            "dateTime" to material.dateTime
            // Add other fields you want to update, but NOT 'view' or 'claimed'
        )

        Tasks.whenAllSuccess<Any>(uploadTasks).addOnSuccessListener {
            Log.d(TAG, "All upload tasks successful, updating Firestore document")
            materialRef.update(updatedFields)
                .addOnSuccessListener {
                    Log.d(TAG, "Material updated successfully in Firestore")
                }
                .addOnFailureListener { e ->
                    Log.e(TAG, "Error updating material in Firestore: $e")
                }
        }.addOnFailureListener { e ->
            Log.e(TAG, "Error in upload tasks: $e")
        }
    }




    fun addMaterial(material: Material, imageUri: Uri?, documentUri: Uri?, subCollectionName: String) {
        val materialRef = db.collection("Materials").document()
        material.id = materialRef.id

        val uploadTasks = mutableListOf<Task<*>>()

        imageUri?.let { uri ->
            val imageRef = storageRef.child("images/${material.id}")
            uploadTasks.add(uploadFileAndGetUrl(uri, imageRef) { url ->
                material.imageUrl = url
            })
        }

        documentUri?.let { uri ->
            val docRef = storageRef.child("documents/${material.id}")
            uploadTasks.add(uploadFileAndGetUrl(uri, docRef) { url ->
                val course = CourseDocument(documentUrl = url)
                materialRef.collection("Courses").add(course)
                    .addOnSuccessListener {
                        Log.d("MaterialViewModel", "Document successfully added to Courses sub-collection")
                    }
                    .addOnFailureListener { e ->
                        Log.e("MaterialViewModel", "Error adding document to Courses sub-collection", e)
                    }
            })
        }

        Tasks.whenAllSuccess<Any>(uploadTasks).addOnSuccessListener {
            val materialRef = db.collection("Materials").document(material.id)
            materialRef.set(material).addOnSuccessListener {
                // Create sub-collection based on isUnique value
                materialRef.collection(subCollectionName).document().set(mapOf("placeholder" to "data"))
                Log.d("MaterialViewModel", "Material and sub-collection added successfully")
            }.addOnFailureListener { e ->
                Log.e("MaterialViewModel", "Error adding material", e)
            }
        }.addOnFailureListener { e ->
            Log.e("MaterialViewModel", "Error in upload tasks", e)
        }
    }


    private fun uploadFileAndGetUrl(fileUri: Uri, storageRef: StorageReference, onSuccess: (String) -> Unit): Task<*> {
        return storageRef.putFile(fileUri).continueWithTask {
            if (!it.isSuccessful) {
                it.exception?.let { exception ->
                    throw exception
                }
            }
            storageRef.downloadUrl
        }.addOnSuccessListener { uri ->
            onSuccess(uri.toString())
        }
    }




    fun fetchMaterialForComment(documentId: String, onComplete: (name: String, imageUrl: String) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        db.collection("Materials").document(documentId)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val name = document.getString("name") ?: ""
                    val imageUrl = document.getString("imageUrl") ?: ""
                    onComplete(name, imageUrl)
                }
            }
    }





}
