package com.cougarsgo.cougarsgo


import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
// @Version 1.2
class RecyclerViewAdapter(var listingArray: Array<ListingModel>) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>(){

    lateinit var onClick: (ListingModel) -> Unit
    var fontsize: Float = 25f

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val viewItem =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view, parent, false)
        return RecyclerViewHolder(viewItem, onClick, fontsize)
    }
    override fun getItemCount(): Int {

        Log.d("size_list", listingArray.size.toString())
        return listingArray.size
    }
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(listingArray[position])
    }
    class RecyclerViewHolder(val viewItem: View, val onClick: (ListingModel) -> Unit, val fontsize: Float) : RecyclerView.ViewHolder(viewItem) {
        fun bind(Listing: ListingModel) {
            viewItem.findViewById<TextView>(R.id.item_view_name).text = Listing.name
            viewItem.findViewById<TextView>(R.id.item_view_name).setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize)
            viewItem.findViewById<TextView>(R.id.item_view_description).text = Listing.description
            viewItem.findViewById<TextView>(R.id.item_view_description).setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize)
            viewItem.findViewById<TextView>(R.id.item_view_price).text = Listing.price.toString()
            viewItem.findViewById<TextView>(R.id.item_view_price).setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize)
            viewItem.findViewById<TextView>(R.id.item_view_color).text = Listing.color
            viewItem.findViewById<TextView>(R.id.item_view_color).setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize)
            viewItem.findViewById<ImageView>(R.id.item_view_image).setImageResource(
                returnImageFromCategory(Listing.category)
            )
            viewItem.setOnClickListener {
                onClick(Listing)
            }

        }
        // Using category to determine which icon is used for the new listing
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

