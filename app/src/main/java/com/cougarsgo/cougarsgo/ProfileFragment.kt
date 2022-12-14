package com.cougarsgo.cougarsgo

import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
// @Version 1.2
class ProfileFragment : Fragment() {

    lateinit var user_name : TextView
    lateinit var user_email : TextView
    lateinit var user_rating: RatingBar
    lateinit var user_img : ImageView
    lateinit var list_recyclerView: RecyclerView
    lateinit var viewManger: RecyclerView.LayoutManager
    lateinit var viewAdapter: RecyclerViewAdapter
    val viewModel: ViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user_name = view.findViewById(R.id.profile_name)
        user_email = view.findViewById(R.id.profile_email)
        user_rating = view.findViewById(R.id.profile_ratingbar)
        user_img = view.findViewById(R.id.profile_img)
        list_recyclerView = view.findViewById(R.id.profile_listing_recycler_view)
        viewManger = LinearLayoutManager(activity)
        viewAdapter = RecyclerViewAdapter(viewModel.listings.value!!.toTypedArray())

        list_recyclerView.layoutManager = viewManger
        list_recyclerView.adapter = viewAdapter

        // To get detail page
        val onClickLambda:(ListingModel) -> Unit = {
            // Current Listing = the listing that was clicked
            viewModel.getCurrentListing(it)
            findNavController().navigate(R.id.action_global_detailFragment)
        }
        viewAdapter.onClick = onClickLambda

        // Filter to get currentUser profile
        viewModel.currentUser.observe(viewLifecycleOwner, {
            user_name.text = it.username
            user_email.text = it.email + ".edu"
            user_rating.rating = it.rating
            // Filter listing based on user
            val listings = viewModel.listings.value!!
            val user_listings = listings.filter{
                it.sellerID == viewModel.currentUser.value?.id
            }
            viewAdapter.listingArray = user_listings.toTypedArray()
            viewAdapter.notifyDataSetChanged()
        })
        /*
            Increase & decrease font size based on which button is clicked. The only values
            that are increased include product name and email.
         */
        viewModel.fontsize.observe(viewLifecycleOwner, {
            val fontsize = viewModel.fontsize.value!!
            viewAdapter.fontsize = fontsize
            user_name.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize)
            user_email.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize)
            viewAdapter.notifyDataSetChanged()
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


}