package com.thevarungupta.grocery_app.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.thevarungupta.grocery_app.R
import com.thevarungupta.grocery_app.app.Endpoints
import kotlinx.android.synthetic.main.activity_add_address.*
import org.json.JSONObject

class AddAddressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_address)

        init()
    }

    private fun init() {
        button_save.setOnClickListener {
            var name = edit_text_name.text.toString()
            var mobile = edit_text_mobile.text.toString()
            var type = edit_text_type.text.toString()
            var houseNo = edit_text_house_no.text.toString()
            var streetName = edit_text_street_name.text.toString()
            var location = edit_text_location.text.toString()
            var city = edit_text_city.text.toString()
            var pincode = edit_text_pincode.text.toString().toInt()
            var userId = "62b1a9c35f652a0017b0fc20"

            var params = JSONObject()
            params.put("name", name)
            params.put("mobile", mobile)
            params.put("houseNo", houseNo)
            params.put("streetName", streetName)
            params.put("location", location)
            params.put("city", city)
            params.put("pincode", pincode)
            params.put("type", "home")
            params.put("userId", userId)

            var requestQueue = Volley.newRequestQueue(this)
            var request = JsonObjectRequest(
                Request.Method.POST,
                Endpoints.saveAddress(),
                params,
                Response.Listener {
                    Toast.makeText(applicationContext, "saved", Toast.LENGTH_SHORT).show()
                },
                Response.ErrorListener {
                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                }
            )
            requestQueue.add(request)
        }
    }
}