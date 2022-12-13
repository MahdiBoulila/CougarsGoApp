package com.example.cougarsgo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
class DetailFragment : Fragment() {

    lateinit var detail_name : TextView
    lateinit var detail_description: TextView
    lateinit var detail_price : TextView
    lateinit var detail_color : TextView
    val viewModel: ViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detail_name = view.findViewById(R.id.detail_product_name)
        detail_description = view.findViewById(R.id.detail_prduct_description)
        detail_price = view.findViewById(R.id.detail_product_condition)
        detail_color = view.findViewById(R.id.detail_seller_name)

        viewModel.currentListing.observe(viewLifecycleOwner, {
            detail_name.text = it.name
            detail_description.text = it.description
            detail_price.text = it.price.toString()
            detail_color.text = it.color
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }
}