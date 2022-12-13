package com.example.cougarsgo


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(var listingArray: Array<ListingModel>) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>(){
    lateinit var onClick: (ListingModel) -> Unit
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val viewItem =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view, parent, false)
        return RecyclerViewHolder(viewItem, onClick)
    }
    override fun getItemCount(): Int {

        Log.d("size_list", listingArray.size.toString())
        return listingArray.size
    }
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(listingArray[position])
    }
    class RecyclerViewHolder(val viewItem: View, val onClick: (ListingModel) -> Unit) : RecyclerView.ViewHolder(viewItem) {
        fun bind(Listing: ListingModel) {
            viewItem.findViewById<TextView>(R.id.item_view_name).text = Listing.name
            viewItem.findViewById<TextView>(R.id.item_view_description).text = Listing.description
            viewItem.findViewById<TextView>(R.id.item_view_price).text = Listing.price.toString()
            viewItem.findViewById<TextView>(R.id.item_view_color).text = Listing.color
            viewItem.setOnClickListener {
                onClick(Listing)
            }
        }
    }
}

