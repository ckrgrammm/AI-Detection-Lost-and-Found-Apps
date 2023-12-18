package com.example.fyps.viewmodel.shopping

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fyps.firebaseDatabase.FirebaseDb
import com.example.fyps.model.*
import com.example.fyps.resource.Resource
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

private const val TAG = "ShoppingViewModel"

class ShoppingViewModel(
    private val firebaseDatabase: FirebaseDb

) : ViewModel() {


    private val _listNews = MutableLiveData<List<News>>()
    val listNews: LiveData<List<News>> get() = _listNews


    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    val materials: MutableLiveData<Resource<List<Material>>> = MutableLiveData()

    private val _materialsLiveData = MutableLiveData<Resource<List<Material>>>()
    val materialsLiveData: LiveData<Resource<List<Material>>> get() = _materialsLiveData

    val searchResults = MutableLiveData<Resource<List<Material>>>()
//    val materials = MutableLiveData<Resource<List<Material>>>()




    val addAddress = MutableLiveData<Resource<Address>>()
    val updateAddress = MutableLiveData<Resource<Address>>()
    val deleteAddress = MutableLiveData<Resource<Address>>()

    val profile = MutableLiveData<Resource<User>>()


    val uploadProfileImage = MutableLiveData<Resource<String>>()
    val updateUserInformation = MutableLiveData<Resource<User>>()
    val passwordReset = MutableLiveData<Resource<String>>()
    val categories = MutableLiveData<Resource<List<Category>>>()
    val search = MutableLiveData<Resource<List<Product>>>()




    // Fetch Materials from Firebase
    fun getMaterials() {
        _materialsLiveData.postValue(Resource.Loading())

        firebaseDatabase.getMaterials(10)
            .addOnSuccessListener { materials ->
                // materials is already a List<Material>
                _materialsLiveData.postValue(Resource.Success(materials))
            }
            .addOnFailureListener { exception ->
                _materialsLiveData.postValue(Resource.Error(exception.message ?: "An unknown error occurred"))
            }
    }


    fun fetchNews() {
        FirebaseFirestore.getInstance().collection("News").get()
            .addOnSuccessListener { documents ->
                val newsItems = documents.toObjects(News::class.java)
                _listNews.postValue(newsItems)
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Error fetching news", e)
                // Handle error
            }
    }

    private fun shouldPaging(category: String, listSize: Int, onSuccess: (Boolean) -> Unit) {
        FirebaseFirestore.getInstance()
            .collection("categories")
            .whereEqualTo("name", category).get().addOnSuccessListener {
                val tempCategory = it.toObjects(Category::class.java)
                val products = tempCategory[0].products
                Log.d("test", " $category : prodcuts ${tempCategory[0].products}, size $listSize")
                if (listSize == products)
                    onSuccess(false).also { Log.d(TAG, "$category Paging:false") }
                else
                    onSuccess(true).also { Log.d(TAG, "$category Paging:true") }
            }
    }

    private fun shouldPagingHome(listSize: Int, onSuccess: (Boolean) -> Unit) {
        FirebaseFirestore.getInstance()
            .collection("categories").get().addOnSuccessListener {
                var productsCount = 0
                it.toObjects(Category::class.java).forEach { category ->
                    productsCount += category.products!!.toInt()
                }

                if (listSize == productsCount)
                    onSuccess(false)
                else
                    onSuccess(true)

            }
    }


//    private fun checkIfProductAlreadyAdded(
//        product: CartProduct,
//        onSuccess: (Boolean, String) -> Unit
//    ) {
//        addToCart.postValue(Resource.Loading())
//        firebaseDatabase.getProductInCart(product).addOnCompleteListener {
//            if (it.isSuccessful) {
//                val documents = it.result!!.documents
//                if (documents.isNotEmpty())
//                    onSuccess(true, documents[0].id) // true ---> product is already in cart
//                else
//                    onSuccess(false, "") // false ---> product is not in cart
//            } else
//                addToCart.postValue(Resource.Error(it.exception.toString()))
//
//        }
//    }




    fun saveAddress(address: Address) {
        addAddress.postValue(Resource.Loading())
        firebaseDatabase.saveNewAddress(address)?.addOnCompleteListener {
            if (it.isSuccessful)
                addAddress.postValue(Resource.Success(address))
            else
                addAddress.postValue(Resource.Error(it.exception.toString()))
        }
    }

    fun updateAddress(oldAddress: Address, newAddress: Address) {
        updateAddress.postValue(Resource.Loading())
        firebaseDatabase.findAddress(oldAddress).addOnCompleteListener { addressResponse ->
            if (addressResponse.isSuccessful) {
                val documentUid = addressResponse.result!!.documents[0].id
                firebaseDatabase.updateAddress(documentUid, newAddress)?.addOnCompleteListener {
                    if (it.isSuccessful)
                        updateAddress.postValue(Resource.Success(newAddress))
                    else
                        updateAddress.postValue(Resource.Error(it.exception.toString()))

                }

            } else
                updateAddress.postValue(Resource.Error(addressResponse.exception.toString()))

        }
    }

    fun deleteAddress(address: Address) {
        deleteAddress.postValue(Resource.Loading())
        firebaseDatabase.findAddress(address).addOnCompleteListener { addressResponse ->
            if (addressResponse.isSuccessful) {
                val documentUid = addressResponse.result!!.documents[0].id
                firebaseDatabase.deleteAddress(documentUid, address)?.addOnCompleteListener {
                    if (it.isSuccessful)
                        deleteAddress.postValue(Resource.Success(address))
                    else
                        deleteAddress.postValue(Resource.Error(it.exception.toString()))

                }

            } else
                deleteAddress.postValue(Resource.Error(addressResponse.exception.toString()))

        }
    }

    private val user: User? = null
    fun getUser() {
        if (user != null) {
            profile.postValue(Resource.Success(user))
            return
        }

        profile.postValue(Resource.Loading())
        firebaseDatabase.getUser().addSnapshotListener { value, error ->
            if (error != null)
                profile.postValue(Resource.Error(error.message))
            else
                profile.postValue(Resource.Success(value?.toObject(User::class.java)))

        }
    }

    fun uploadProfileImageAndUpdateInformation(image: ByteArray, firstName: String, lastName: String, email: String, status: Status?) {
        uploadProfileImage.postValue(Resource.Loading())
        val name = UUID.nameUUIDFromBytes(image).toString()

        // Upload the image
        firebaseDatabase.uploadUserProfileImage(image, name).addOnCompleteListener { uploadTask ->
            if (uploadTask.isSuccessful) {
                // After successful upload, get the URL and update user information
                firebaseDatabase.getImageUrl(firstName, lastName, email, name) { user, exception ->
                    if (exception != null) {
                        updateUserInformation.postValue(Resource.Error(exception))
                    } else {
                        user?.let {
                            // Check for null status and set it if it's not null
                            if (status != null) {
                                it.status = status
                            }
                            updateUserInformation(it)
                        }
                    }
                }
            } else {
                uploadProfileImage.postValue(Resource.Error(uploadTask.exception.toString()))
            }
        }
    }


    private fun updateUserInformation(user: User) {
        updateUserInformation.postValue(Resource.Loading())
        firebaseDatabase.updateUserInformation(user).addOnCompleteListener {
            if (it.isSuccessful) {
                updateUserInformation.postValue(Resource.Success(user))
            } else {
                updateUserInformation.postValue(Resource.Error(it.exception.toString()))
            }
        }
    }




    fun updateInformation(firstName: String, lastName: String, email: String, imageName: String, status: Status?) {
        updateUserInformation.postValue(Resource.Loading())

        firebaseDatabase.getImageUrl(firstName, lastName, email, imageName) { user, exception ->
            if (exception != null) {
                updateUserInformation.postValue(Resource.Error(exception))
            } else {
                user?.let {
                    // Set the status from the parameter to ensure it's preserved
                    it.status = status ?: it.status // Use the existing status if the new one is null
                    onUpdateInformation(it)
                }
            }
        }
    }


    private fun onUpdateInformation(user: User) {
        firebaseDatabase.updateUserInformation(user).addOnCompleteListener {
            if (it.isSuccessful)
                updateUserInformation.postValue(Resource.Success(user))
            else
                updateUserInformation.postValue(Resource.Error(it.exception.toString()))

        }
    }


    fun resetPassword(email: String) {
        passwordReset.postValue(Resource.Loading())
        firebaseDatabase.resetPassword(email).addOnCompleteListener {
            if (it.isSuccessful)
                passwordReset.postValue(Resource.Success(email))
            else
                passwordReset.postValue(Resource.Error(it.exception.toString()))
        }
    }



    fun searchMaterials(query: String) {
        Log.d(TAG, "Searching for: $query")
        searchResults.postValue(Resource.Loading())

        firestore.collection("Materials")
            .get().addOnSuccessListener { documents ->
                val allMaterials = documents.map { document -> document.toObject(Material::class.java) }

                // Filter materials based on the name containing the query
                val results = allMaterials.filter { it.name.contains(query, ignoreCase = true) }
                Log.d(TAG, "Found ${results.size} results")

                searchResults.postValue(Resource.Success(results))
            }.addOnFailureListener { exception ->
                searchResults.postValue(Resource.Error("Error fetching data"))
                Log.e(TAG, "Error getting documents: ", exception)
            }
    }




    private var categoriesSafe: List<Category>? = null
    fun getCategories() {
        if(categoriesSafe != null){
            categories.postValue(Resource.Success(categoriesSafe))
            return
        }
        categories.postValue(Resource.Loading())
        firebaseDatabase.getCategories().addOnCompleteListener {
            if (it.isSuccessful) {
                val categoriesList = it.result!!.toObjects(Category::class.java)
                categoriesSafe = categoriesList
                categories.postValue(Resource.Success(categoriesList))
            } else
                categories.postValue(Resource.Error(it.exception.toString()))
        }

    }

}