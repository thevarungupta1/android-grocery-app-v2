package com.thevarungupta.grocery_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.thevarungupta.grocery_app.R
import com.thevarungupta.grocery_app.adapters.AddressAdapter
import com.thevarungupta.grocery_app.app.Endpoints
import com.thevarungupta.grocery_app.models.AddressResponse
import kotlinx.android.synthetic.main.activity_address.*

class AddressActivity : AppCompatActivity() {

    lateinit var addressAdapter: AddressAdapter
    val userId = "62b1a9c35f652a0017b0fc20"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        init()
    }

    private fun init() {
        getData()
        button_address_add.setOnClickListener {
            startActivity(Intent(this, AddAddressActivity::class.java))
        }

        addressAdapter = AddressAdapter(this)
        recycler_view.adapter = addressAdapter
        recycler_view.layoutManager = LinearLayoutManager(this)

    }

    private fun getData() {
        var requestQueue = Volley.newRequestQueue(this)
        var request = StringRequest(
            Request.Method.GET,
            Endpoints.getAddressByUserId(userId),
            Response.Listener {
                var gson = Gson()
                var addressResponse = gson.fromJson(it, AddressResponse::class.java)
                addressAdapter.setData(addressResponse.data)
            },
            Response.ErrorListener {

            }
        )
        requestQueue.add(request)
    }
}