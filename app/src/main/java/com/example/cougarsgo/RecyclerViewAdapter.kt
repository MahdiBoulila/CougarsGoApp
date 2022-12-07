package com.example.cougarsgo

/*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(var listingArray: Array<ListingModel>) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>(){


    lateinit var onClick: (ListingModel) -> Unit


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val viewItem =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view_normal, parent, false)
        return RecyclerViewHolder(viewItem, onClick)
    }

    override fun getItemCount(): Int {
        return listingArray.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(listingArray[position])
    }

    class RecyclerViewHolder(val viewItem: View, val onClick: (ListingModel) -> Unit) : RecyclerView.ViewHolder(viewItem) {
        fun bind(Listing: ListingModel) {


            /*
            viewItem.findViewById<TextView>(R.id.lv_company).text = Restaurant.name
            viewItem.findViewById<TextView>(R.id.lv_style).text = Restaurant.style
            viewItem.findViewById<TextView>(R.id.lv_service).text = Restaurant.service

            viewItem.setOnClickListener {
                onClick(Restaurant)
            }

             */


        }
    }
}

 */