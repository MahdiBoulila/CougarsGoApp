package com.example.cougarsgo

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

class DetailFragment : Fragment() {

    lateinit var detail_name : TextView
    lateinit var seller_name : TextView
    lateinit var detail_description: TextView
    lateinit var detail_category : TextView
    lateinit var detail_price : TextView
    lateinit var detail_color : TextView
    lateinit var detail_condition : TextView
    lateinit var detail_delete_button : Button
    lateinit var contact_btn: Button
    val viewModel: ViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contact_btn = view.findViewById(R.id.detail_contact_button)
        detail_name = view.findViewById(R.id.detail_product_name)
        seller_name = view.findViewById(R.id.detail_seller_name)
        detail_description = view.findViewById(R.id.detail_product_description)
        detail_price = view.findViewById(R.id.detail_product_price)
        detail_color = view.findViewById(R.id.detail_product_color)
        detail_category = view.findViewById(R.id.detail_product_category)
        detail_delete_button = view.findViewById(R.id.detail_delete_button)

        //get user from listing's id
        val current_listing = viewModel.currentListing.value!!
        val id = current_listing.sellerID
        val seller = viewModel.getUserFromID(id)

        viewModel.currentListing.observe(viewLifecycleOwner, {
            detail_name.text = it.name
            seller_name.text = viewModel.currentUser.value?.username
            detail_description.text = it.description
            detail_price.text = it.price.toString()
            detail_color.text = it.color
        })

        // Delete button only appears if the listing was created by the current user
        detail_delete_button.setVisibility(View.INVISIBLE);
        if (viewModel.currentUser.value?.id == viewModel.currentListing.value?.sellerID) {
            detail_delete_button.setVisibility(View.VISIBLE)
            detail_delete_button.setOnClickListener {
                viewModel.removeCurrentListing(viewModel.currentListing.value?.id)
                findNavController().navigate(R.id.action_global_listingsFragment)
            }
        }

        viewModel.fontsize.observe(viewLifecycleOwner, {
            val fontsize = viewModel.fontsize.value!!
            detail_name.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize)
            seller_name.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize)
            detail_description.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize)
        })

        contact_btn.setOnClickListener{
                val mailto = "mailto:${seller?.email}" +
                        "?cc=no-reply@cougarsgo.edu" +
                        "&subject=" + Uri.encode("CougarsGo: Potential Buyer for '${detail_name.text}', ID:${current_listing.id.takeLast(4)}") +
                        "&body=" + Uri.encode("Hello Clarkie!")
                val emailIntent = Intent(Intent.ACTION_SENDTO)
                emailIntent.data = Uri.parse(mailto)
                try {
                    startActivity(emailIntent)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(context, "Error to open email app", Toast.LENGTH_SHORT).show()
                }
            }

        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }
}