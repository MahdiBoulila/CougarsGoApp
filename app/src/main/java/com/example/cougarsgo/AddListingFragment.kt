package com.example.cougarsgo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import java.util.*


class AddListingFragment : Fragment() {
    lateinit var name_edit: EditText
    lateinit var description_edit: EditText
    lateinit var price_edit: EditText
    lateinit var create_button: Button
    val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_listing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        name_edit = view.findViewById(R.id.listing_name_edittext)
        description_edit = view.findViewById(R.id.listing_description_edittext)
        price_edit = view.findViewById(R.id.listing_price_edittext)
        create_button = view.findViewById(R.id.create_listing_button)
        val currentUser = viewModel.currentUser.value!!

        create_button.setOnClickListener{
            val name = name_edit.text.toString()
            val description = description_edit.text.toString()
            val price = price_edit.text.toString()
            if ((name.isNotBlank() && description.isNotBlank() && price.isNotBlank())){
                val id = UUID.randomUUID().toString()
                //TODO Waiting for front end to add color
                val listing = ListingModel(id, name, description, currentUser.id, price.toInt(), "white", "")
                viewModel.insertNewListing(listing)
                viewModel.addListingToCurrentUser(id)
                Toast.makeText(activity, "Successfully Created A Listing!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_global_listingsFragment)
            } else {
                Toast.makeText(
                    activity,
                    "Make sure to fill every box.",
                    Toast.LENGTH_SHORT
                )
            }
        }
    }
}