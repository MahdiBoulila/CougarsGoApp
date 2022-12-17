package com.example.cougarsgo

import android.os.Bundle
import android.util.Patterns.EMAIL_ADDRESS
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import java.util.*
import kotlin.random.Random

class SignupFragment : Fragment() {
    val viewModel: ViewModel by activityViewModels()

    lateinit var email_edittext: EditText
    lateinit var password_edittext: EditText
    lateinit var signup_button : Button
    lateinit var username_edittext: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.signup_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signup_button = view.findViewById(R.id.signup_button)
        email_edittext = view.findViewById(R.id.signup_email_edittext)
        password_edittext = view.findViewById(R.id.signup_password_edittext)
        username_edittext = view.findViewById(R.id.signup_username_edittext)

        signup_button.setOnClickListener{
            val email = email_edittext.text.toString()
            val password = password_edittext.text.toString()
            val username = username_edittext.text.toString()
            if(email.isEmpty()){
                email_edittext.setError("Email is required")
                email_edittext.requestFocus()
                return@setOnClickListener
            }
            else if(!email.contains("@clarku.edu")){
                email_edittext.setError("Please provide a valid clark email")
                email_edittext.requestFocus()
                return@setOnClickListener
            }
            else if(password.isEmpty()){
                password_edittext.setError("Password is required.")
                password_edittext.requestFocus()
                return@setOnClickListener
            }
            else if(password.length < 6){
                password_edittext.setError("Minimum password length is 6.")
                password_edittext.requestFocus()
                return@setOnClickListener
            }
            else if ((username.length < 3) && (username.length > 10)){
                username_edittext.setError("username length is between 3 and 10.")
                username_edittext.requestFocus()
                return@setOnClickListener
            }

            else  {
                val id = UUID.randomUUID().toString()
                val user = UserModel(email = email.dropLast(4), password = password, id = id, username = username)
                viewModel.createUser(user)
                viewModel.setCurrentUser(user)
                Toast.makeText(this.requireActivity(), "User Created. Successfully Logged in!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_global_listingsFragment)
            }

        }
    }
}