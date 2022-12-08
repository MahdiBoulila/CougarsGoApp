package com.example.cougarsgo
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ViewModel: ViewModel(), ValueEventListener {
    val database = MutableLiveData<DatabaseReference>()
    val currentUser = MutableLiveData<UserModel>()
    val listings = MutableLiveData<List<ListingModel>>()
    val fontsize = MutableLiveData<Number>()
    val users = MutableLiveData<List<UserModel>>()

    init{
        currentUser.value = UserModel()
        users.value = emptyList()
        listings.value = emptyList<ListingModel>()
        fontsize.value = 24
        database.value = Firebase.database.getReference("")
        database.value?.addValueEventListener(this)
    }


    fun insertNewListing(){

    }

    fun updateUserListings(){

    }

    fun updateDatabase(){

    }

    fun createUser(user: UserModel) {
        if (user != null) {
            currentUser.value=user
            currentUser.postValue(currentUser.value)
            database.value?.child(user.id.toString())?.setValue(user)
        }
    }

    override fun onDataChange(snapshot: DataSnapshot) {
        val users = ArrayList<UserModel>()
        snapshot.children.forEach{
//            Log.e("MAHDI ", it.value.toString())
            val user = it.getValue(UserModel::class.java)
            if(user != null){
                users.add(user)
            }
        }
        this.users.value = users
        this.users.postValue(users)
    }

    override fun onCancelled(error: DatabaseError) {
        TODO("Not yet implemented")
    }


}