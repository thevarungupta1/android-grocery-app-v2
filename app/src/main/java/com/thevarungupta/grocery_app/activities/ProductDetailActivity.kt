package com.thevarungupta.grocery_app.activities

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.squareup.picasso.Picasso
import com.thevarungupta.grocery_app.R
import com.thevarungupta.grocery_app.app.Config
import com.thevarungupta.grocery_app.database.DbHelper
import com.thevarungupta.grocery_app.models.Product
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.row_product_adapter.view.*
import kotlin.math.min

class ProductDetailActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var dbHelper: DbHelper
    var product: Product? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        dbHelper = DbHelper(this)

        product = intent.getSerializableExtra(Product.KEY_PRODUCT) as Product

        init()

    }

    private fun init() {
        updateUi()
        setData()
        button_add_to_cart.setOnClickListener(this)
        button_add.setOnClickListener(this)
        button_minus.setOnClickListener(this)
    }

    private fun setData(){
        Picasso
            .get()
            .load(Config.IMAGE_URL + product?.image)
            .into(image_view_product)

        text_view_product_name.text = product?.productName
        text_view_unit.text = product?.unit

        text_view_price.text = getString(R.string.Rs) + product?.price.toString()


    }

    override fun onClick(v: View?) {
        when(v){
            button_add_to_cart -> addToCart()
            button_add -> addQuantity()
            button_minus -> minusQuantity()
        }
    }

    private fun addToCart() {
        product?.quality = 1
        dbHelper.addItemInCart(product!!)
        updateUi()
    }

    private fun addQuantity(){
        product!!.quality = product!!.quality?.plus(1)
        dbHelper.updateItemQty(product!!)
        updateUi()
    }

    private fun minusQuantity(){
        product!!.quality = product?.quality?.minus(1)
        if(product!!.quality!! > 0){
            dbHelper.updateItemQty(product!!)
        }else{
            dbHelper.deleteItemFromCart(product!!)
        }
        updateUi()
    }
    private fun updateUi(){
        var quality = dbHelper.getItemQuantityFromCart(product!!)
        if(quality > 0){
            button_add_to_cart.visibility = View.GONE
            layout_button.visibility = View.VISIBLE
            text_view_qty.text = quality.toString()
        }else{
            button_add_to_cart.visibility = View.VISIBLE
            layout_button.visibility = View.GONE
        }
    }
}