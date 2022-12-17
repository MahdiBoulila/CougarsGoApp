package com.example.cougarsgo


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
            viewItem.findViewById<ImageView>(R.id.item_view_image).setImageResource(
                returnImageFromCategory(Listing.category)
            )
            viewItem.setOnClickListener {
                onClick(Listing)
            }
        }
        fun returnImageFromCategory(category : String): Int{

            if(category == "Electronics") {
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
    }


}

