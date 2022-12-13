package com.example.cougarsgo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.findNavController

// @Version 1.0
class MainActivity : AppCompatActivity() {

    // ViewModel
    val viewModel : ViewModel by viewModels<ViewModel>()

    // Load restaurant data into this arraylist
    var data = ArrayList<ListingModel>()


    /**
     * read the CSV data
     * */
    fun loadData() {


        val res = ListingModel(
            "fewnfj",
            "cells[1]",
            "cells[2]",
            "cells[3]",
            "cells[4]",
            "cells[5]",
            "fnekfje"
        )
        // Add res to your view model
        data.add(res)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()
        Log.d("list", data.toString())

        // Add
        viewModel.test.value = data.toTypedArray()



    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // Handle item selection

        // Checks which item is being clicked
        if (item.itemId == R.id.menu_home) {
            findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_listingsFragment)
            Toast.makeText(this, "Home Selected", Toast.LENGTH_SHORT).show()
            return true
        }
        else if (item.itemId == R.id.menu_login) {
            findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_loginFragment)
            Toast.makeText(this, "Log In Selected", Toast.LENGTH_SHORT).show()
            return true
        }
        else if (item.itemId == R.id.menu_signup) {
            findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_signupFragment)
            Toast.makeText(this, "Sign Up Selected", Toast.LENGTH_SHORT).show()
            return true
        }
        else if (item.itemId == R.id.menu_newlisting) {
            findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_addListingFragment)
            Toast.makeText(this, "New Listing Selected", Toast.LENGTH_SHORT).show()
            return true
        }
        else if (item.itemId == R.id.menu_newlisting) {
            findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_addListingFragment)
            Toast.makeText(this, "New Listing Selected", Toast.LENGTH_SHORT).show()
            return true
        }
        else {
            return super.onOptionsItemSelected(item)
        }

    }
}