package com.cougarsgo.cougarsgo

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
// @Version 1.2
class DetailFragment : Fragment() {

    lateinit var detail_name : TextView
    lateinit var seller_name : TextView
    lateinit var detail_description: TextView
    lateinit var detail_category : TextView
    lateinit var detail_price : TextView
    lateinit var detail_color : TextView
    lateinit var detail_img : ImageView
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
        detail_img = view.findViewById(R.id.detail_scrollview_img)


        // Get user from listing's id
        val current_listing = viewModel.currentListing.value!!
        val id = current_listing.sellerID
        val seller = viewModel.getUserFromID(id)

        // Find image of current listing
        fun returnImageFromCategory(category : String): Int {
            if (category == "Electronics") {
                return R.drawable.ic_baseline_computer_24
            } else if (category == "Food") {
                return R.drawable.ic_baseline_fastfood_24
            } else if (category == "Clothing") {
                return R.drawable.ic_baseline_checkroom_24
            } else if (category == "Cleaning Supplies") {
                return R.drawable.ic_baseline_cleaning_services_24
            } else if (category == "Furniture") {
                return R.drawable.ic_baseline_chair_24
            } else if (category == "Academic") {
                return R.drawable.ic_baseline_menu_book_24
            } else if (category == "Arts and Crafts") {
                return R.drawable.ic_baseline_format_paint_24
            } else {
                return R.drawable.ic_baseline_question_mark_24
            }
        }

        // Show detail of current listing
        viewModel.currentListing.observe(viewLifecycleOwner, {
            detail_name.text = it.name
            detail_description.text = it.description
            detail_price.text = it.price.toString()
            detail_color.text = it.color
            detail_category.text = it.category
            detail_img.setImageResource(returnImageFromCategory(it.category))
            seller_name.text = seller?.username
        })

        /*
            Delete button only appears if the listing was created by the current user
         */
        detail_delete_button.setVisibility(View.INVISIBLE);
        if (viewModel.currentUser.value?.id == viewModel.currentListing.value?.sellerID) {
            detail_delete_button.setVisibility(View.VISIBLE)
            detail_delete_button.setOnClickListener {
                viewModel.removeCurrentListing(viewModel.currentListing.value?.id)
                findNavController().navigate(R.id.action_global_listingsFragment)
            }
        }

        /*
            Increase & decrease font size based on which button is clicked.The only values
            that are increased include product name, seller name and email.
         */
        viewModel.fontsize.observe(viewLifecycleOwner, {
            val fontsize = viewModel.fontsize.value!!
            detail_name.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize)
            seller_name.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize)
            detail_description.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize)
        })
        // To reach out to seller
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