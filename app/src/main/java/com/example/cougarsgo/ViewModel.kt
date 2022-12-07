package com.example.cougarsgo
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModel: ViewModel() {
    val database = MutableLiveData<UserDB>()
    val currentUser = MutableLiveData<UserModel>()
    val listings = MutableLiveData<List<ListingModel>>()

    init{
        currentUser.value = UserModel()
        listings.value = emptyList<ListingModel>()
    }

    fun initDatabase ( applicationContext: Context){
        database.value = UserDB.getDBObject(applicationContext)
    }

    fun insertNewListing(){

    }

    fun updateUserListings(){

    }

    fun updateDatabase(){

    }

    fun registerUser(userName: String) {
        if (currentUser.value == null || currentUser.value?.username != userName) {
            val user = UserModel()
            user.username = userName
            currentUser.value=user
            currentUser.postValue(currentUser.value)
        }
    }


}