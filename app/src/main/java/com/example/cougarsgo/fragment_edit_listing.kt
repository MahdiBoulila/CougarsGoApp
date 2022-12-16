package com.example.cougarsgo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels


/**
 * A simple [Fragment] subclass.
 * Use the [fragment_edit_listing.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_edit_listing : Fragment() {

    /*
    lateinit var edit_button : Button
    lateinit var edit_listing_name: EditText
    lateinit var edit_listing_description: EditText
    lateinit var edit_listing_condition : EditText
    lateinit var edit_listing_price : EditText
    val viewModel: ViewModel by activityViewModels<ViewModel>()
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_listing, container, false)
    }


}