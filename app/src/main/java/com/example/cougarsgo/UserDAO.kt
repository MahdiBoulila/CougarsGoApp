package com.example.cougarsgo

import androidx.room.*

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user : UserModel)


    @Query ("select * from usersTable where email like :email")
    fun userExists(email: String) : UserModel

    @Query("select * from usersTable")
    fun getAllUsers():List<UserModel>

    @Query("select listings from usersTable")
    fun getAllListings(): List<ListingModel>

    @Delete
    fun deleteUser(user:UserModel)

    @Delete
    fun deleteListing(listing: ListingModel)

    @Query("delete from usersTable")
    fun deleteAll()
}