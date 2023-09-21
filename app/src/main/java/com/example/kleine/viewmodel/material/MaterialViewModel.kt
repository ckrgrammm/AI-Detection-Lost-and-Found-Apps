package com.example.kleine.viewmodel.material

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kleine.model.MaterialData
import com.example.kleine.model.MaterialEngageData
import com.google.firebase.firestore.FirebaseFirestore

class MaterialViewModel : ViewModel() {
    val materialEngageData = MutableLiveData<MaterialEngageData?>()
    private val _materialList = MutableLiveData<List<MaterialData>>()
    val materialList: LiveData<List<MaterialData>> = _materialList

    fun fetchMaterialsData() {
        val db = FirebaseFirestore.getInstance()
        db.collection("Materials")
            .get()
            .addOnSuccessListener { result ->
                val tempList = ArrayList<MaterialData>()
                for (document in result) {
                    val id = document.id
                    val materialName = document.getString("name") ?: ""
                    val description = document.getString("desc") ?: ""
                    val requirement = document.getString("requirement") ?: ""
                    val rating = document.getDouble("rating") ?: 0.0
                    val imageUrl = document.getString("imageUrl") ?: ""
                    tempList.add(MaterialData(id, materialName, description, requirement, rating, imageUrl))
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

}
