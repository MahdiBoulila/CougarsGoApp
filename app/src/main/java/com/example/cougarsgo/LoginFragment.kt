package com.example.cougarsgo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import kotlin.random.Random

class LoginFragment : Fragment() {
    lateinit var email_edittext: EditText
    lateinit var password_edittext: EditText
    lateinit var login_button: Button
    lateinit var user_name : TextView
    val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.login_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login_button = view.findViewById(R.id.login_button)
        email_edittext = view.findViewById(R.id.login_email_edittext)
        password_edittext = view.findViewById(R.id.login_password_edittext)
        // user_name = view.findViewById(R.id.activity_main_welcome)

        login_button.setOnClickListener {
            var email = email_edittext.text.toString()
            val password = password_edittext.text.toString()
            // var username = viewModel.currentUser.value?.username
            if (email.isEmpty()) {
                email_edittext.setError("Email is required")
                email_edittext.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                password_edittext.setError("Password is required")
                password_edittext.requestFocus()
                return@setOnClickListener
            }
            else {
                email = email.dropLast(4)
                if (viewModel.isUserInDatabase(email)) {
                    val userFromDatabase = viewModel.getUserFromDatabase(email, password)
                    if (userFromDatabase != null) {
                        viewModel.setCurrentUser(userFromDatabase)
                        Toast.makeText(
                            this.requireActivity(),
                            "Welcome: " + userFromDatabase?.email,
                            Toast.LENGTH_SHORT
                        ).show()
                        findNavController().navigate(R.id.action_global_listingsFragment)
                    }
                } else {
                    Toast.makeText(
                        this.requireActivity(),
                        "Incorrect email or password.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}

