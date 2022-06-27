package com.thevarungupta.grocery_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.thevarungupta.grocery_app.R
import com.thevarungupta.grocery_app.adapters.CategoryAdapter
import com.thevarungupta.grocery_app.app.Endpoints
import com.thevarungupta.grocery_app.models.CategoryResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.context_main.*
import kotlinx.android.synthetic.main.nav_header.view.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var categoryAdapter: CategoryAdapter

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this, AddressActivity::class.java))
        init()
    }

    private fun setupToolbar(){
        var toolbar = toolbar
        toolbar.title = "Home"
        setSupportActionBar(toolbar)
    }

    private fun init() {
        setupToolbar()
        drawerLayout = drawer_layout
        navView = nav_view
        var headerView = navView.getHeaderView(0)

        headerView.text_view_header_name.text = "Paul"
        headerView.text_view_header_email.text = "p@gmail.com"

        val toggler  =ActionBarDrawerToggle(
            this, drawerLayout,toolbar, 0 , 0
        )
        drawerLayout.addDrawerListener(toggler)
        toggler.syncState()
        navView.setNavigationItemSelectedListener(this)

        categoryAdapter = CategoryAdapter(this)
        recycler_view.adapter = categoryAdapter
        recycler_view.layoutManager = GridLayoutManager(this, 2)


        var requestQueue = Volley.newRequestQueue(this)
        var request = StringRequest(
            Request.Method.GET,
            Endpoints.getCategoryUrl(),
            Response.Listener {
                var gson = Gson()
                var categoryResponse = gson.fromJson(it, CategoryResponse::class.java)
                categoryAdapter.setData(categoryResponse.data)
            },
            Response.ErrorListener {

            }
        )
        requestQueue.add(request)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_cart -> Toast.makeText(applicationContext, "cart clicked", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_account -> {
                Toast.makeText(applicationContext, "account", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }
}