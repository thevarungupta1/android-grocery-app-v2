package com.thevarungupta.grocery_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.thevarungupta.grocery_app.R
import com.thevarungupta.grocery_app.adapters.ProductAdapter
import com.thevarungupta.grocery_app.app.Endpoints
import com.thevarungupta.grocery_app.models.ProductResponse
import kotlinx.android.synthetic.main.fragment_product.view.*


private const val ARG_SUB_ID = "sub_id"

class ProductFragment : Fragment() {

    private var subId:Int = 0
    lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            subId = it.getInt(ARG_SUB_ID)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_product, container, false)

        init(view)
        return view
    }

    private fun init(view: View) {
        getData()
        productAdapter = ProductAdapter(requireActivity())
        view.recycler_view.adapter = productAdapter
        view.recycler_view.layoutManager = LinearLayoutManager(activity)
    }

    private fun getData(){
        var requestQueue = Volley.newRequestQueue(activity)
        var request = StringRequest(
            Request.Method.GET,
            Endpoints.getProductBySubId(subId),
            Response.Listener {
                              var gson = Gson()
                var productResponse =  gson.fromJson(it, ProductResponse::class.java)
                productAdapter.setData(productResponse.data)
            },
            Response.ErrorListener {
                Toast.makeText(activity, ""+ it.message, Toast.LENGTH_SHORT).show()
            }
        )
        requestQueue.add(request)
    }

    companion object {

        @JvmStatic
        fun newInstance(subId: Int) =
            ProductFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SUB_ID, subId)

                }
            }
    }
}