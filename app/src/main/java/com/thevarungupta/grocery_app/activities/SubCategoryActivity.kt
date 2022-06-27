package com.thevarungupta.grocery_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.notification.ZenPolicy
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.thevarungupta.grocery_app.R
import com.thevarungupta.grocery_app.adapters.MyFragmentAdapter
import com.thevarungupta.grocery_app.app.Endpoints
import com.thevarungupta.grocery_app.models.Category
import com.thevarungupta.grocery_app.models.SubCategoryResponse
import kotlinx.android.synthetic.main.activity_sub_category.*

class SubCategoryActivity : AppCompatActivity() {

    private var catId = 0
    lateinit var myFragmentAdapter: MyFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_category)

        catId = intent.getIntExtra(Category.KEY_CAT_ID, 1)
        Toast.makeText(applicationContext, catId.toString(), Toast.LENGTH_SHORT).show()

        init()
    }

    private fun init() {
        getData()
        myFragmentAdapter = MyFragmentAdapter(supportFragmentManager)

    }

    fun getData() {
        var requestQueue = Volley.newRequestQueue(this)
        var request = StringRequest(
            Request.Method.GET,
            Endpoints.getSubCategoryByCatId(catId),
            Response.Listener {
                var gson = Gson()
                var subCategoryResponse =  gson.fromJson(it, SubCategoryResponse::class.java);
                for(sub in subCategoryResponse.data){
                    myFragmentAdapter.addFragment(sub)
                }
                view_pager.adapter = myFragmentAdapter
                tab_layout.setupWithViewPager(view_pager)
            },
            Response.ErrorListener {

            }
        )
        requestQueue.add(request)
    }
}