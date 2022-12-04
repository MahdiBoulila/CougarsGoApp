package com.example.cougarsgo

import androidx.room.*

@Dao
interface UserDAO {
    @Query("select * from usersTable")
    fun getAllUsers():List<UserModel>

    @Query("select listings from usersTable")
    fun getAllListings(): List<ListingModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user:UserModel)

    @Delete
    fun deleteUser(user:UserModel)

    @Delete
    fun deleteListing(listing: ListingModel)

    @Query("delete from usersTable")
    fun deleteAll()
}