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
import com.google.android.material.bottomnavigation.BottomNavigationView

// @Version 1.0
class MainActivity : AppCompatActivity() {

    // ViewModel
    val viewModel : ViewModel by viewModels<ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener {
            if (it.itemId == R.id.bottom_menu_home){
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_listingsFragment)
                Toast.makeText(this, "Home Selected", Toast.LENGTH_SHORT).show()
                return@setOnItemSelectedListener true
            }
            else if(it.itemId == R.id.bottom_menu_newlisting) {
                if (viewModel.currentUser.value?.email?.isBlank() == true) {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_loginFragment)
                    Toast.makeText(this, "Please Login first", Toast.LENGTH_SHORT).show()
                    return@setOnItemSelectedListener true
                }
                else {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_addListingFragment)
                    Toast.makeText(this, "New Listing Selected", Toast.LENGTH_SHORT).show()
                    return@setOnItemSelectedListener true
                }
            }
            else{
                return@setOnItemSelectedListener true
            }
        }
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
            if (viewModel.currentUser.value?.email?.isBlank() == true) {
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_loginFragment)
                Toast.makeText(this, "Please Login first", Toast.LENGTH_SHORT).show()
                return true
            }
            else {
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_addListingFragment)
                Toast.makeText(this, "New Listing Selected", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        else if (item.itemId == R.id.menu_profile) {
            if (viewModel.currentUser.value?.email?.isBlank() == true) {
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_loginFragment)
                Toast.makeText(this, "Please Login first", Toast.LENGTH_SHORT).show()
                return true
            }
            else {
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_profileFragment)
                Toast.makeText(this, "Profile Selected", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        else {
            return super.onOptionsItemSelected(item)
        }

    }
}