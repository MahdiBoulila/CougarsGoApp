package com.example.cougarsgo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import kotlin.math.sign

class SignupFragment : Fragment() {
    val viewModel: ViewModel by activityViewModels()

    lateinit var email_edittext: EditText
    lateinit var password_edittext: EditText
    lateinit var signup_button : Button
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

        signup_button.setOnClickListener{
            val email = email_edittext.text.toString()
            val password = password_edittext.text.toString()
            if (email.isNotBlank() && password.isNotBlank()) {
                val user = UserModel(email = email, password = password)
                viewModel.createUser(user)
            }
        }
    }
}