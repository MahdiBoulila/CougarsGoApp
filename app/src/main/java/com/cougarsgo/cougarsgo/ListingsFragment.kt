package com.cougarsgo.cougarsgo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// @Version 1.2
class ListingsFragment : Fragment() {

    lateinit var list_recyclerView: RecyclerView
    lateinit var viewManger: RecyclerView.LayoutManager
    lateinit var viewAdapter: RecyclerViewAdapter
    lateinit var searchView : SearchView
    val viewModel: ViewModel by activityViewModels<ViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManger = LinearLayoutManager(activity)
        // Get listing arraylist from viewmodel
        val entire_list = viewModel.listings.value!!
        viewAdapter = RecyclerViewAdapter(entire_list.toTypedArray())
        // Get list recyclerview
        list_recyclerView = view.findViewById(R.id.listing_recycler_view)
        list_recyclerView.layoutManager = viewManger
        list_recyclerView.adapter = viewAdapter

        searchView = view.findViewById(R.id.listing_searchview)
        searchView.queryHint = "Search"

        val onClickLambda:(ListingModel) -> Unit = {
            // Current Listing = the listing that was clicked
            viewModel.getCurrentListing(it)
            findNavController().navigate(R.id.action_global_detailFragment)
        }
        viewAdapter.onClick = onClickLambda

        // This shows the information
        // This method is triggered by postvalue
        viewModel.listings.observe(viewLifecycleOwner, {
            viewAdapter.listingArray = viewModel.listings.value!!.toTypedArray()
            viewAdapter.notifyDataSetChanged()
        })

        viewModel.fontsize.observe(viewLifecycleOwner, {
            viewAdapter.fontsize = viewModel.fontsize.value!!
            viewAdapter.notifyDataSetChanged()
        })

        // Filter based on whatever the user inputs
        fun filter(search_text: String): ArrayList<ListingModel> {
            val filter_list_by_search: ArrayList<ListingModel> = ArrayList()
            for (listing in viewAdapter.listingArray) {
                if (listing.name.contains(search_text) || (listing.description.contains(search_text))) {
                    filter_list_by_search.add(listing)
                }
            }
            return filter_list_by_search
        }
        // Perform search function using the helper fiter method
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isNotBlank()) {
                    viewModel.listings.observe(viewLifecycleOwner, {
                        viewAdapter.listingArray = filter(newText).toTypedArray()
                        viewAdapter.notifyDataSetChanged()
                    })
                }
                else {
                    viewModel.listings.observe(viewLifecycleOwner, {
                        viewAdapter.listingArray = viewModel.listings.value!!.toTypedArray()
                        viewAdapter.notifyDataSetChanged()
                    })
                }
                return false
            }
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