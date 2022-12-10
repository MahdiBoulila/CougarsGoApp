package com.example.cougarsgo
import android.util.Log
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
    val listings = MutableLiveData<ArrayList<ListingModel>>()
    val fontsize = MutableLiveData<Number>()
    val users = MutableLiveData<List<UserModel>>()

    // List of restaurants from csv file
    val test = MutableLiveData<Array<ListingModel>>()

    init{
        currentUser.value = UserModel()
        users.value = emptyList()
        listings.value = ArrayList<ListingModel>()
        fontsize.value = 24
        database.value = Firebase.database.getReference("")
        database.value?.addValueEventListener(this)

        test.value = emptyArray()

    }

    fun getList() : Array<ListingModel> {

        // Holds all the entire list
        var listing_list = emptyArray<ListingModel>()

        if (test.value != null) {
            listing_list = test.value!!
        }

        Log.d("entire_list", listing_list.toString())
        return listing_list


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

        val listings = ArrayList<ListingModel>()

        snapshot.children.forEach{
//            Log.e("MAHDI ", it.value.toString())
            val user = it.getValue(UserModel::class.java)

            // val listing = it.getValue(ListingModel::class.java)

            if(user != null){
                users.add(user)


            }

            /*
            // Add listing to listings arraylist
            if (listing != null) {
                listings.add(listing)
            }

             */
        }
        this.users.value = users
        this.users.postValue(users)


        /*
        this.listings.value = listings
        // Post message value
        this.listings.postValue(listings)

         */

    }

    override fun onCancelled(error: DatabaseError) {
        TODO("Not yet implemented")
    }


}