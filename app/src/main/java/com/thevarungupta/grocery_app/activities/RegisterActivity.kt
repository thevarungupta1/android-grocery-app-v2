package com.thevarungupta.grocery_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.thevarungupta.grocery_app.R
import com.thevarungupta.grocery_app.app.Endpoints
import com.thevarungupta.grocery_app.models.User
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()
    }

    private fun init() {
        button_register.setOnClickListener {
            var firstName = edit_text_first_name.text.toString()
            var email = edit_text_email.text.toString()
            var mobile = edit_text_mobile.text.toString()
            var password = edit_text_password.text.toString()
            var user = User(firstName = firstName, email = email, mobile = mobile, password = password)
            postRequest(user)
        }
    }

    private fun postRequest(user: User){

        var params = HashMap<String, String>()
        params["firstName"] = user.firstName!!
        params["email"] = user.email!!
        params["mobile"] = user.mobile!!
        params["password"] = user.password!!

        var jsonObject = JSONObject(params as Map<*, *>?)


        var requestQueue = Volley.newRequestQueue(this)
        var request = JsonObjectRequest(
            Request.Method.POST,
            Endpoints.getRegisterUrl(),
            jsonObject,
            Response.Listener {
                startActivity(Intent(applicationContext, LoginActivity::class.java))
                Toast.makeText(applicationContext, "Register successfully", Toast.LENGTH_SHORT).show()
            },
            Response.ErrorListener {
                Log.d("abc", it.message.toString())
            }
        )
        requestQueue.add(request)
    }
}