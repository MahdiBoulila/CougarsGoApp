package com.example.cougarsgo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 * A simple [Fragment] subclass.
 * Use the [ListingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListingsFragment : Fragment() {

    lateinit var list_recyclerView: RecyclerView
    lateinit var viewManger: RecyclerView.LayoutManager
    lateinit var viewAdapter: RecyclerViewAdapter


    val viewModel: ViewModel by activityViewModels<ViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // viewManger = GridLayoutManager(activity, 2)
        viewManger = LinearLayoutManager(activity)

        // Get listing arraylist from viewmodel
        val entire_list = viewModel.listings.value!!
        viewAdapter = RecyclerViewAdapter(entire_list.toTypedArray())

        // Get list recyclerview
        list_recyclerView = view.findViewById(R.id.listing_recycler_view)

        list_recyclerView.layoutManager = viewManger
        list_recyclerView.adapter = viewAdapter


        val onClickLambda:(ListingModel) -> Unit = {
            // Current Listing = the listing that was clicked
            viewModel.getCurrentListing(it)
            findNavController().navigate(R.id.action_global_detailFragment)
        }
        viewAdapter.onClick = onClickLambda

        // This shows the information
        // This method is triggered by postvalue
        viewModel.listings.observe(viewLifecycleOwner, {
            val listings = viewModel.listings.value!!
            viewAdapter.listingArray = listings.toTypedArray()
            viewAdapter.notifyDataSetChanged()
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_listings, container, false)
    }


}