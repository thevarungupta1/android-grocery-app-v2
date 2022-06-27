package com.thevarungupta.grocery_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thevarungupta.grocery_app.R
import com.thevarungupta.grocery_app.database.DbHelper
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity : AppCompatActivity() {

    lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        dbHelper = DbHelper(this)

        init()
    }

    private fun init(){
        button_checkout.setOnClickListener {
            startActivity(Intent(this, AddressActivity::class.java))
        }
    }

}