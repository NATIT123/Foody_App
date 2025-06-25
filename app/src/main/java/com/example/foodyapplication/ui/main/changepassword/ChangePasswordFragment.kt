package com.example.foodyapplication.ui.main.changepassword

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.foodyapplication.R
import com.example.foodyapplication.base.fragment.BaseFragment

class ChangePasswordFragment : BaseFragment() {

    private lateinit var edtOldPassword: EditText
    private lateinit var edtNewPassword: EditText
    private lateinit var edtConfirmPassword: EditText
    private lateinit var btnSave: Button
    private lateinit var tvForgotPassword: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_change_password, container, false)

        edtOldPassword = view.findViewById(R.id.edtOldPassword)
        edtNewPassword = view.findViewById(R.id.edtNewPassword)
        edtConfirmPassword = view.findViewById(R.id.edtConfirmPassword)
        btnSave = view.findViewById(R.id.btnSave)
        tvForgotPassword = view.findViewById(R.id.tvForgotPassword)

        btnSave.setOnClickListener {
            val old = edtOldPassword.text.toString()
            val new = edtNewPassword.text.toString()
            val confirm = edtConfirmPassword.text.toString()

            if (new != confirm) {
                Toast.makeText(context, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Gọi viewModel để thực hiện đổi mật khẩu
        }

        tvForgotPassword.setOnClickListener {

        }

        return view
    }
}
