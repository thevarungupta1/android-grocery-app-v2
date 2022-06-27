package com.thevarungupta.grocery_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.thevarungupta.grocery_app.R
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        init()
    }

    private fun init() {
        button_login.setOnClickListener(this)
        button_register.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            button_login -> startActivity(Intent(this, LoginActivity::class.java))
            button_register -> startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}