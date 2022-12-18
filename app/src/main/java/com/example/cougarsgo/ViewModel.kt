package com.example.cougarsgo
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
    val listings = MutableLiveData<ArrayList<ListingModel>>()
    val fontsize = MutableLiveData<Number>()
    val users = MutableLiveData<ArrayList<UserModel>>()

    // Current Listing
    val currentListing = MutableLiveData<ListingModel>()
    val currentUser = MutableLiveData<UserModel>()

    init {
        currentUser.value = UserModel()
        users.value = ArrayList<UserModel>()
        listings.value = ArrayList()
        fontsize.value = 24f
        database.value = Firebase.database.getReference("")
        database.value?.addValueEventListener(this)
    }

    fun createUser(user: UserModel) {
        if (user != null) {
            currentUser.value=user
            currentUser.postValue(currentUser.value)
            database.value?.child("users")?.child(user.email)?.setValue(user)
        }
    }

    // to fetch detail
    fun getCurrentListing(listing: ListingModel){
        // Test
//        Log.d("currentlisting", listing.toString())
        currentListing.value = listing
        currentListing.postValue(listing)
    }
    fun setCurrentUser(user: UserModel){
        currentUser.value = user
        currentUser.postValue(user)
    }
    fun removeCurrentListing(id: String?){
        if(currentListing.value != null){
            database.value?.child("listings")?.child(id.toString())?.removeValue()
        }
    }
    fun insertNewListing(listing: ListingModel){
        if(currentUser.value != null) {
            database.value?.child("listings")?.child(listing.id.toString())?.setValue(listing)
        }
    }
    fun addListingToCurrentUser(id : String){
        if (id != null){
            currentUser.value?.listingsID?.add(id)
        }
    }
    fun isUserInDatabase(email: String): Boolean{
        val users = users.value!!
        users.forEach{ user ->
            if (user.email == email) return true
        }
        return false
    }
    fun getUserFromDatabase(email: String, password: String): UserModel? {
        val users = users.value!!
        users.forEach{ user ->
            if ( (user.email == email) && (user.password == password)){
                return user
            }
        }
        return null
    }
    override fun onDataChange(snapshot: DataSnapshot) {
        val users = ArrayList<UserModel>()
        val listings = ArrayList<ListingModel>()

        snapshot.child("users").children.forEach{
            val user = it.getValue(UserModel::class.java)
            if(user != null){
                users.add(user)
            }
        }
        this.users.value = users
        this.users.postValue(users)

        snapshot.child("listings").children.forEach{
            val listing = it.getValue(ListingModel::class.java)
            if(listing != null){
                listings.add(listing)
            }
        }
        this.listings.value = listings
        this.listings.postValue(listings)

    }

    override fun onCancelled(error: DatabaseError) {
        TODO("Not yet implemented")
    }


}