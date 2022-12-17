package com.example.cougarsgo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import org.w3c.dom.Text
import java.util.*


class AddListingFragment : Fragment() {
    lateinit var add_name: EditText
    lateinit var add_description: EditText
    lateinit var add_price: EditText
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
        add_name = view.findViewById(R.id.listing_name_edittext)
        add_description = view.findViewById(R.id.listing_description_edittext)
        add_price = view.findViewById(R.id.listing_price_edittext)
        create_button = view.findViewById(R.id.create_listing_button)
        val currentUser = viewModel.currentUser.value!!
        val colors = resources.getStringArray(R.array.color_arrays)
        val products = resources.getStringArray(R.array.product_arrays)

        val color_dropdown = view.findViewById<Spinner>(R.id.listing_color_dropdown)
        var picked_color = ""
        if (color_dropdown != null) {
            ArrayAdapter(
                this.requireActivity(),
                android.R.layout.simple_spinner_item,
                colors
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                color_dropdown.adapter = adapter
            }
            color_dropdown.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                   picked_color = color_dropdown.selectedItem.toString()
                    // Log.d("color", picked_color)
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        val product_dropdown = view.findViewById<Spinner>(R.id.listing_product_dropdown)
        var picked_category = ""
        if (product_dropdown != null) {
            ArrayAdapter(
                this.requireActivity(),
                android.R.layout.simple_spinner_item,
                products
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                product_dropdown.adapter = adapter
            }
            product_dropdown.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    picked_category = product_dropdown.selectedItem.toString()
                    // Log.d("category", picked_color)
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }
        }

        create_button.setOnClickListener{
            val name = add_name.text.toString()
            val description = add_description.text.toString()
            val price = add_price.text.toString()

            if ((name.isNotBlank() && description.isNotBlank() && price.isNotBlank())){
                val id = UUID.randomUUID().toString()
                val listing = ListingModel(id, name, description, currentUser.id, price.toInt(), picked_color , picked_category)
                viewModel.insertNewListing(listing)
                viewModel.addListingToCurrentUser(id)
                Toast.makeText(activity, "Successfully Created A Listing!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_global_listingsFragment)
            } else {
                if (name.isEmpty()) {
                    add_name.setError("Product Name is required")
                    add_name.requestFocus()
                    return@setOnClickListener
                }
                if (description.isEmpty()) {
                    add_description.setError("Product Description is required")
                    add_description.requestFocus()
                    return@setOnClickListener
                }
                if (description.length < 30) {
                    add_description.setError("Minimum description length is 30 characters")
                    add_description.requestFocus()
                    return@setOnClickListener
                }
                if (price.isEmpty()) {
                    add_price.setError("Product Price is required")
                    add_price.requestFocus()
                    return@setOnClickListener
                }
            }
        }
    }
}